package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.AlbumModel
import com.kvw.technicaltestmediamonks.business.models.UserModel
import com.kvw.technicaltestmediamonks.data.retrofit.models.Album
import com.kvw.technicaltestmediamonks.data.retrofit.models.Photo
import com.kvw.technicaltestmediamonks.data.retrofit.services.AlbumService
import com.kvw.technicaltestmediamonks.data.retrofit.services.PhotoService
import com.kvw.technicaltestmediamonks.data.room.dao.AlbumDAO
import com.kvw.technicaltestmediamonks.data.room.dao.PhotoDAO
import com.kvw.technicaltestmediamonks.data.room.dto.AlbumDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class AlbumRepositoryImpl(
    private val albumService: AlbumService,
    private val photoService: PhotoService,
    private val albumDAO: AlbumDAO,
    private val photoDAO: PhotoDAO
): AlbumRepository{
    override suspend fun getByUser(userModel: UserModel): List<AlbumModel> {
        Timber.d("Fetching albums by user: %s", userModel)

        return albumDAO.getAlbumByUserId(userModel.id)
            .mapNotNull { album ->
                photoDAO.getFirstPhotoFromAlbum(album.id)?.let { AlbumModel(album, it) }
            }
            .ifEmpty { fetchAndCacheAlbums(userModel) }
            .also { Timber.d("Fetched albums from user %s", userModel) }
    }

    private suspend fun fetchAndCacheAlbums(userModel: UserModel): List<AlbumModel>{
        Timber.d("Fetching from remote")
        var insertJob: Job
        return albumService
            .getByUserId(userModel.id)
            .associateWith { photoService.getByAlbumId(it.id).first() }
            .also { albumWithThumbMap ->
                insertJob = coroutineScope {
                    launch(Dispatchers.IO) { cacheAlbums(albumWithThumbMap, userModel.id) }
                }
            }
            .map { AlbumModel(it.key, it.value) }
            .also { insertJob.join() }
    }

    private suspend fun cacheAlbums(albumWithPhotoMap: Map<Album, Photo>, userId: Int){
        albumDAO.insertAlbums(
            *albumWithPhotoMap
                .map {
                    AlbumDTO(
                        it.key.id,
                        userId,
                        it.key.title,
                        it.value.thumbnailUrl
                    )
                }
                .toTypedArray()
        )
    }
}

