package com.kvw.technicaltestmediamonks.business.repositories

import com.kvw.technicaltestmediamonks.business.models.UserModel
import com.kvw.technicaltestmediamonks.data.retrofit.models.User
import com.kvw.technicaltestmediamonks.data.retrofit.services.UserService
import com.kvw.technicaltestmediamonks.data.room.dao.UserDAO
import com.kvw.technicaltestmediamonks.data.room.dto.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class UserRepositoryImpl(
    private val userService: UserService,
    private val userDAO: UserDAO
) : UserRepository {
    override suspend fun getAllUsers(): List<UserModel> {
        Timber.d("Fetching all users")
        return try {
            userDAO.getAll()
                .map { UserModel(it) }
                .ifEmpty {
                    fetchAndCacheUsers()
                }.also { Timber.d("Users fetched") }
        } catch (t: Throwable) {
            Timber.e(t)
            emptyList()
        }
    }

    private suspend fun fetchAndCacheUsers(): List<UserModel> {
        Timber.d("Fetching from remote")
        var cacheJob: Job
        return userService.getAll()
            .also { cacheJob = coroutineScope { launch(Dispatchers.IO) { cacheUsers(it) } } }
            .map { UserModel(it) }
            .also { cacheJob.join() }
    }

    private suspend fun cacheUsers(users: List<User>) {
        userDAO.insertUsers(
            *users.map { UserDTO(it.id, it.name, it.username, it.email) }.toTypedArray()
        )
    }
}