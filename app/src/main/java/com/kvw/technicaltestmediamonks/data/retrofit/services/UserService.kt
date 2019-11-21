package com.kvw.technicaltestmediamonks.data.retrofit.services

import com.kvw.technicaltestmediamonks.data.retrofit.models.User
import retrofit2.http.GET

interface UserService {
    @GET("/users")
    suspend fun getAll(): List<User>
}