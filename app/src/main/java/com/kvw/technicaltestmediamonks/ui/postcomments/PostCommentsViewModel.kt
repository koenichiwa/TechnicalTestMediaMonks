package com.kvw.technicaltestmediamonks.ui.postcomments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.technicaltestmediamonks.business.models.CommentModel
import com.kvw.technicaltestmediamonks.business.models.PostModel
import com.kvw.technicaltestmediamonks.business.repositories.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostCommentsViewModel(
    private val commentRepository: CommentRepository,
    private val _post: PostModel
) : ViewModel() {

    val post: LiveData<PostModel> = MutableLiveData<PostModel>(_post)

    private val _comments = MutableLiveData<List<CommentModel>>(emptyList())
    val comments: LiveData<List<CommentModel>> get() = _comments

    fun onLoad() {
        viewModelScope.launch(Dispatchers.IO) {
            _comments.postValue(commentRepository.getCommentsByPost(_post))
        }
    }
}
