package com.kvw.technicaltestmediamonks.ui.albumphotos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kvw.technicaltestmediamonks.R


class AlbumPhotosFragment : Fragment() {

    companion object {
        fun newInstance() =
            AlbumPhotosFragment()
    }

    private lateinit var viewModel: AlbumPhotosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_photos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AlbumPhotosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
