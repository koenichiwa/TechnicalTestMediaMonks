package com.kvw.technicaltestmediamonks.business.models

import com.kvw.technicaltestmediamonks.data.retrofit.models.User

class UserModel(val id: Int, val name: String){
    constructor(user: User): this(user.id, user.name)
}