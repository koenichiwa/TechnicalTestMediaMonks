package com.kvw.technicaltestmediamonks.business.models

import android.os.Parcelable
import com.kvw.technicaltestmediamonks.data.retrofit.models.User
import com.kvw.technicaltestmediamonks.data.room.dto.UserDTO
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserModel(val id: Int, val name: String, val username: String, val email: String) : Parcelable {
    constructor(user: User): this(user.id, user.name, user.username, user.email)
    constructor(userDTO: UserDTO): this(userDTO.id, userDTO.name, userDTO.username, userDTO.email)
}