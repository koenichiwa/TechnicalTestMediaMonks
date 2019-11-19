package com.kvw.technicaltestmediamonks.services

import com.kvw.technicaltestmediamonks.models.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {
    @GET("/albums?userId={userId")
    suspend fun getByUserId(@Path("userId") userId: Int): List<Album>
}