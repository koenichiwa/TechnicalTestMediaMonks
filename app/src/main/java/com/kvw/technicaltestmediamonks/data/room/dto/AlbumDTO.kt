package com.kvw.technicaltestmediamonks.data.room.dto

import android.net.Uri
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class AlbumDTO(
    @PrimaryKey
    val id: Int,
    @ForeignKey(entity = UserDTO::class, parentColumns = ["id"], childColumns = ["userId"])
    val userId: Int,
    val title: String,
    val thumbnailUrl: Uri
)