package com.kvw.technicaltestmediamonks.ui.useralbums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.technicaltestmediamonks.business.models.AlbumModel
import com.kvw.technicaltestmediamonks.business.models.UserModel
import com.kvw.technicaltestmediamonks.business.repositories.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserAlbumsViewModel(albumRepository: AlbumRepository, _user: UserModel) : ViewModel() {
    val user: LiveData<UserModel> = MutableLiveData(_user)

    private val _albums = MutableLiveData<List<AlbumModel>>(emptyList())
    val albums: LiveData<List<AlbumModel>> get() = _albums

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _albums.postValue(albumRepository.getByUser(_user))
        }
    }
}
