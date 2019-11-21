package com.kvw.technicaltestmediamonks.data.room.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDTO(@PrimaryKey val id: Int, val name: String, val username: String, val email: String)