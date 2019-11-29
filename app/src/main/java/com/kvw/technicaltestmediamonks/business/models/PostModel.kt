package com.kvw.technicaltestmediamonks.business.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostModel(val id: Int, val title: String, val body: String) : Parcelable