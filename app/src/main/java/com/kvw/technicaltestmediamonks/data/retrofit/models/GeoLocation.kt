package com.kvw.technicaltestmediamonks.data.retrofit.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeoLocation(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lng") val longitude: Double
) : Parcelable