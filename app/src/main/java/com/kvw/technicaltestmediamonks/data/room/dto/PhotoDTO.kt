package com.kvw.technicaltestmediamonks.data.room.dto

import android.net.Uri
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class PhotoDTO(
    @PrimaryKey
    val id: Int,
    @ForeignKey(entity = AlbumDTO::class, parentColumns = ["id"], childColumns = ["albumId"])
    val albumId: Int,
    val title: String,
    val thumnailUrl: Uri,
    val url: Uri
)