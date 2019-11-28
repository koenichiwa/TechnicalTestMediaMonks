package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.AlbumModel
import com.kvw.technicaltestmediamonks.business.models.UserModel

interface AlbumRepository {
    suspend fun getByUser(userModel: UserModel): List<AlbumModel>
}