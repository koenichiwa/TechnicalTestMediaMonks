package com.kvw.technicaltestmediamonks.data.retrofit.services

import com.kvw.technicaltestmediamonks.data.retrofit.models.Comment
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentService {
    @GET("/comments")
    suspend fun getByPost(@Query("postId") postId: Int): List<Comment>
}