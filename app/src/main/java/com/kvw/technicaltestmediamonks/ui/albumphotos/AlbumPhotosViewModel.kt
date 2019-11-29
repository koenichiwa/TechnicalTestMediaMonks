package com.kvw.technicaltestmediamonks.ui.albumphotos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.technicaltestmediamonks.business.models.AlbumModel
import com.kvw.technicaltestmediamonks.business.models.PhotoModel
import com.kvw.technicaltestmediamonks.business.repositories.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumPhotosViewModel(
    private val photoRepository: PhotoRepository,
    private val _album: AlbumModel
) : ViewModel() {
    val album: LiveData<AlbumModel> = MutableLiveData(_album)

    private val _photos: MutableLiveData<List<PhotoModel>> = MutableLiveData(emptyList())
    val photos: LiveData<List<PhotoModel>> get() = _photos

    fun onLoad() {
        viewModelScope.launch(Dispatchers.IO) {
            _photos.postValue(photoRepository.getPhotosByAlbum(_album))
        }
    }
}
