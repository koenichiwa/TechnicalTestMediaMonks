package com.kvw.technicaltestmediamonks.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kvw.technicaltestmediamonks.models.User

class UsersViewModel : ViewModel() {

    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: LiveData<List<User>> get() = _users

    init {
        TODO("fill _users")
    }
}
