package com.kvw.technicaltestmediamonks.ui.albumphotos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kvw.technicaltestmediamonks.models.Album
import com.kvw.technicaltestmediamonks.models.Photo
import com.kvw.technicaltestmediamonks.services.PhotoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumPhotosViewModel(photoService: PhotoService, _album: Album) : ViewModel() {
    val album: LiveData<Album> = MutableLiveData(_album)

    private val _photos: MutableLiveData<List<Photo>> = MutableLiveData(emptyList())
    val photos: LiveData<List<Photo>> get() = _photos

    init {
        viewModelScope.launch(Dispatchers.IO) { _photos.postValue(photoService.getByAlbumId(_album.id)) }
    }
}
