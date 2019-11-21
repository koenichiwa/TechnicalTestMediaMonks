package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.AlbumModel
import com.kvw.technicaltestmediamonks.business.models.UserModel
import com.kvw.technicaltestmediamonks.data.retrofit.services.AlbumService
import com.kvw.technicaltestmediamonks.data.retrofit.services.PhotoService

interface AlbumRepository{
    suspend fun getByUser(userModel: UserModel): List<AlbumModel>
}