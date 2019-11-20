package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.UserModel
import com.kvw.technicaltestmediamonks.data.retrofit.services.UserService

class UserRepositoryImpl(private val userService: UserService): UserRepository {
    override suspend fun getAllUsers(): List<UserModel> {
        return userService.getAll().map { UserModel(it) }
    }
}