package com.kvw.technicaltestmediamonks.business.models

import android.net.Uri
import android.os.Parcelable
import com.kvw.technicaltestmediamonks.data.retrofit.models.Photo
import com.kvw.technicaltestmediamonks.data.room.dto.PhotoDTO
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoModel(val id: Int, val title: String, val url: Uri) : Parcelable {
    constructor(photo: Photo) : this(photo.id, photo.title, photo.url)
    constructor(photoDTO: PhotoDTO) : this(photoDTO.id, photoDTO.title, photoDTO.url)
}