package com.kvw.technicaltestmediamonks.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kvw.technicaltestmediamonks.data.room.dto.PhotoDTO

@Dao
interface PhotoDAO {
    @Query("SELECT * FROM PhotoDTO WHERE albumId = :albumId")
    suspend fun getPhotosByAlbumId(albumId: Int): List<PhotoDTO>

    @Query("SELECT * FROM PhotoDTO WHERE albumId = :albumId LIMIT 1")
    suspend fun getFirstPhotoFromAlbum(albumId: Int): PhotoDTO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(vararg photoDTO: PhotoDTO)
}