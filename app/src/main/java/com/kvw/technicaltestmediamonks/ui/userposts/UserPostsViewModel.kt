package com.kvw.technicaltestmediamonks.ui.userposts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.technicaltestmediamonks.business.models.PostModel
import com.kvw.technicaltestmediamonks.business.models.UserModel
import com.kvw.technicaltestmediamonks.business.repositories.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserPostsViewModel(postRepository: PostRepository, _userModel: UserModel) : ViewModel() {
    val user: LiveData<UserModel> = MutableLiveData(_userModel)

    private val _posts = MutableLiveData<List<PostModel>>(emptyList())
    val posts: LiveData<List<PostModel>> get() = _posts

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _posts.postValue(postRepository.getPostsByUser(_userModel))
        }
    }
}
