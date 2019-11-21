package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.UserModel

interface UserRepository {
    suspend fun getAllUsers(): List<UserModel>
}