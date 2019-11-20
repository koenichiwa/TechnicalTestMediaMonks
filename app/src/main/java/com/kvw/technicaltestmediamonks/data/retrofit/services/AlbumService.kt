package com.kvw.technicaltestmediamonks.data.retrofit.services

import com.kvw.technicaltestmediamonks.data.retrofit.models.Album
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {
    @GET("/albums")
    suspend fun getByUserId(@Query("userId") userId: Int): List<Album>
}