package com.kvw.technicaltestmediamonks.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kvw.technicaltestmediamonks.data.room.dto.UserDTO

@Dao
interface UserDAO {
    @Query("SELECT * FROM UserDTO")
    suspend fun getAll(): List<UserDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg userDTO: UserDTO)
}