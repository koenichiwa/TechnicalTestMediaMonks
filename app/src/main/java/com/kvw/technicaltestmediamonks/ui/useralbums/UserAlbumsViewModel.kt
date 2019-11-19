package com.kvw.technicaltestmediamonks.ui.useralbums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.technicaltestmediamonks.models.Album
import com.kvw.technicaltestmediamonks.models.User
import com.kvw.technicaltestmediamonks.services.AlbumService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserAlbumsViewModel(albumService: AlbumService, _user: User) : ViewModel() {
    val user: LiveData<User> = MutableLiveData(_user)

    private val _albums = MutableLiveData<List<Album>>(emptyList())
    val albums: LiveData<List<Album>> get() = _albums

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _albums.postValue(albumService.getByUserId(_user.id))
        }
    }
}
