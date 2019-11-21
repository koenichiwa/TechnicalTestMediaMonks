package com.kvw.technicaltestmediamonks.data.retrofit.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(val name: String, val catchPhrase: String, val bs: String): Parcelable