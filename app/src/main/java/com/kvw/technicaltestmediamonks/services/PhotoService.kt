package com.kvw.technicaltestmediamonks.services

import com.kvw.technicaltestmediamonks.models.Photo
import retrofit2.http.GET
import retrofit2.http.Query


interface PhotoService {
    @GET("/photos")
    suspend fun getByAlbumId(@Query("albumId") albumId: Int): List<Photo>
}