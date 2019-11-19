package com.kvw.technicaltestmediamonks.services

import com.kvw.technicaltestmediamonks.models.User
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    suspend fun getAll(): List<User>
}