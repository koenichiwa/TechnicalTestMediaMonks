package com.kvw.technicaltestmediamonks.ui.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.technicaltestmediamonks.business.models.AlbumModel
import com.kvw.technicaltestmediamonks.business.models.PostModel
import com.kvw.technicaltestmediamonks.business.models.UserModel
import com.kvw.technicaltestmediamonks.business.repositories.AlbumRepository
import com.kvw.technicaltestmediamonks.business.repositories.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailViewModel(
    private val postRepository: PostRepository,
    private val albumRepository: AlbumRepository,
    private val _user: UserModel
) : ViewModel() {

    private val _posts = MutableLiveData<List<PostModel>>(emptyList())
    val posts: LiveData<List<PostModel>> get() = _posts

    private val _albums = MutableLiveData<List<AlbumModel>>(emptyList())
    val albums: LiveData<List<AlbumModel>> get() = _albums

    fun onLoad() {
        viewModelScope.launch(Dispatchers.IO) {
            _posts.postValue(postRepository.getPostsByUser(_user))
        }
        viewModelScope.launch(Dispatchers.IO) {
            _albums.postValue(albumRepository.getByUser(_user))
        }
    }
}
