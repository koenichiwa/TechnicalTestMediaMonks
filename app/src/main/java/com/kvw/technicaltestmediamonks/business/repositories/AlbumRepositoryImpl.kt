package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.AlbumModel
import com.kvw.technicaltestmediamonks.business.models.UserModel
import com.kvw.technicaltestmediamonks.data.retrofit.services.AlbumService
import com.kvw.technicaltestmediamonks.data.retrofit.services.PhotoService

class AlbumRepositoryImpl(
    private val albumService: AlbumService,
    private val photoService: PhotoService
): AlbumRepository{
    override suspend fun getByUser(userModel: UserModel): List<AlbumModel> {
        return albumService.getByUserId(userModel.id)
            .map { AlbumModel(it, photoService.getByAlbumId(it.id).first()) }
    }
}