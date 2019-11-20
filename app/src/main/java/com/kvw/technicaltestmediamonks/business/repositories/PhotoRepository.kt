package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.AlbumModel
import com.kvw.technicaltestmediamonks.business.models.PhotoModel

interface PhotoRepository {
    suspend fun getPhotosByAlbum(albumModel: AlbumModel): List<PhotoModel>
}