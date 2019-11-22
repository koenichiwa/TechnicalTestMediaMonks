package com.kvw.technicaltestmediamonks.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.technicaltestmediamonks.business.models.UserModel
import com.kvw.technicaltestmediamonks.business.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel(userRepository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<UserModel>>(emptyList())
    val users: LiveData<List<UserModel>> get() = _users

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _users.postValue(userRepository.getAllUsers())
        }
    }
}
