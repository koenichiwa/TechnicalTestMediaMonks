package com.kvw.technicaltestmediamonks.ui.albumphotos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.kvw.technicaltestmediamonks.R
import kotlinx.android.synthetic.main.album_photos_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AlbumPhotosFragment : Fragment() {

    private val args: AlbumPhotosFragmentArgs by navArgs()
    private val albumPhotosViewModel: AlbumPhotosViewModel by viewModel { parametersOf(args.album) }

    companion object {
        fun newInstance() =
            AlbumPhotosFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_photos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        albumPhotosViewModel.photos.observe(this, Observer {
            recyclerView_albumPhotos_photos.swapAdapter(AlbumPhotosAdapter(it), true)
        })
    }
}
