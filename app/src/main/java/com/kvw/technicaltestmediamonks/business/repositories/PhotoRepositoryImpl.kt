package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.AlbumModel
import com.kvw.technicaltestmediamonks.business.models.PhotoModel
import com.kvw.technicaltestmediamonks.data.retrofit.models.Photo
import com.kvw.technicaltestmediamonks.data.retrofit.services.PhotoService
import com.kvw.technicaltestmediamonks.data.room.dao.PhotoDAO
import com.kvw.technicaltestmediamonks.data.room.dto.PhotoDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class PhotoRepositoryImpl(
    private val photoService: PhotoService,
    private val photoDAO: PhotoDAO
): PhotoRepository {

    override suspend fun getPhotosByAlbum(albumModel: AlbumModel): List<PhotoModel> {
        Timber.d("Fetching all photos in album %s", albumModel)
        return photoDAO.getPhotosByAlbumId(albumModel.id) //fetch from cache
            .map { PhotoModel(it) }
            .ifEmpty { fetchAncCachePhotos(albumModel) }
            .also { Timber.d("Photos fetched from album %s", albumModel) }
    }

    private suspend fun fetchAncCachePhotos(albumModel: AlbumModel): List<PhotoModel>{
        Timber.d("Fetching from remote")
        var cacheJob: Job
        return photoService.getByAlbumId(albumModel.id) //if cache empty fetch from API
            .also { photoList ->                 //fill cache
                cacheJob = coroutineScope {
                    launch(Dispatchers.IO) { cachePhotos(photoList, albumModel) }
                }
            }.map { PhotoModel(it) }
            .also { cacheJob.join() }
    }

    private suspend fun cachePhotos(photos: List<Photo>, albumModel: AlbumModel){
        photoDAO.insertPhotos(
            *photos.map {
                PhotoDTO(
                    it.id,
                    albumModel.id,
                    it.title,
                    it.thumbnailUrl,
                    it.url
                )
            }.toTypedArray()
        )
    }
}