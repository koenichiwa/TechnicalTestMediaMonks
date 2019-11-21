package com.kvw.technicaltestmediamonks.data.retrofit.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val street: String,
    val suite: String,
    val city: String, val zipcode: String,
    @SerializedName("geo") val geoLocation: GeoLocation
): Parcelable