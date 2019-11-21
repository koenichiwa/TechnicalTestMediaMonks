package com.kvw.technicaltestmediamonks.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kvw.technicaltestmediamonks.data.room.dao.AlbumDAO
import com.kvw.technicaltestmediamonks.data.room.dao.PhotoDAO
import com.kvw.technicaltestmediamonks.data.room.dao.UserDAO
import com.kvw.technicaltestmediamonks.data.room.dto.AlbumDTO
import com.kvw.technicaltestmediamonks.data.room.dto.PhotoDTO
import com.kvw.technicaltestmediamonks.data.room.dto.UserDTO
import com.kvw.technicaltestmediamonks.data.room.typeconverters.Converters

@Database(entities = [UserDTO::class, PhotoDTO::class, AlbumDTO::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun userDAO(): UserDAO
    abstract fun albumDAO(): AlbumDAO
    abstract fun photoDAO(): PhotoDAO
}