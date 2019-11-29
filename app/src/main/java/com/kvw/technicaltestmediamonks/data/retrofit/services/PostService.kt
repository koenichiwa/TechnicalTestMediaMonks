package com.kvw.technicaltestmediamonks.data.retrofit.services

import com.kvw.technicaltestmediamonks.data.retrofit.models.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    @GET("/posts")
    suspend fun getByUser(@Query("userId") userId: Int): List<Post>
}