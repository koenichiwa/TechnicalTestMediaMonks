package com.kvw.technicaltestmediamonks.data.room.typeconverters

import android.net.Uri
import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun uriToString(uri: Uri): String{
        return uri.toString()
    }

    @TypeConverter
    fun stringToUri(string: String): Uri{
        return Uri.parse(string)
    }
}