package com.kvw.technicaltestmediamonks.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kvw.technicaltestmediamonks.data.room.dto.AlbumDTO

@Dao
interface AlbumDAO {
    @Query("SELECT * FROM AlbumDTO WHERE userId = :userId")
    suspend fun getAlbumByUserId(userId: Int): List<AlbumDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(vararg albumDTO: AlbumDTO)
}