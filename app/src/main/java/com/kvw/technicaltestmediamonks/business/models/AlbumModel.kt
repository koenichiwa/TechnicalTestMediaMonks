package com.kvw.technicaltestmediamonks.business.models

import android.net.Uri
import android.os.Parcelable
import com.kvw.technicaltestmediamonks.data.retrofit.models.Album
import com.kvw.technicaltestmediamonks.data.retrofit.models.Photo
import com.kvw.technicaltestmediamonks.data.room.dto.AlbumDTO
import com.kvw.technicaltestmediamonks.data.room.dto.PhotoDTO
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumModel(val id: Int, val title: String, val thumbnailUri: Uri): Parcelable{
    constructor(album: Album, photo: Photo): this(album.id, album.title, photo.thumbnailUrl)
    constructor(albumDTO: AlbumDTO, photoDTO: PhotoDTO)
            : this(albumDTO.id, albumDTO.title, photoDTO.thumnailUrl)
}