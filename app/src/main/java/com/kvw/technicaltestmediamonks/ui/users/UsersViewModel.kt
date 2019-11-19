package com.kvw.technicaltestmediamonks.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.technicaltestmediamonks.models.User
import com.kvw.technicaltestmediamonks.services.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {
    lateinit var userService: UserService //TODO Get this through DI

    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: LiveData<List<User>> get() = _users

    init {
        viewModelScope.launch(Dispatchers.IO) { _users.postValue(userService.getAll()) }
    }
}
