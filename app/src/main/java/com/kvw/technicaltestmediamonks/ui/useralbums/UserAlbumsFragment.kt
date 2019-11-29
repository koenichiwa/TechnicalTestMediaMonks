package com.kvw.technicaltestmediamonks.ui.useralbums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kvw.technicaltestmediamonks.R
import com.kvw.technicaltestmediamonks.ui.userdetail.UserDetailFragmentDirections
import com.kvw.technicaltestmediamonks.ui.userdetail.UserDetailViewModel
import kotlinx.android.synthetic.main.user_albums_fragment.*

class UserAlbumsFragment(_userDetailViewModel: UserDetailViewModel) : Fragment() {
//    private val args: UserAlbumsFragmentArgs by navArgs()
//    private val userAlbumsViewModel: UserAlbumsViewModel by viewModel { parametersOf(args.user) }

    private val userDetailViewModel: UserDetailViewModel = _userDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_albums_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userDetailViewModel.albums.observe(this, Observer { albumList ->
            recyclerView_userAlbums_albums.swapAdapter(
                UserAlbumsAdapter(albumList) { album ->
                    parentFragment?.findNavController()?.navigate(
                        UserDetailFragmentDirections
                            .actionUserDetailFragmentToAlbumPhotosFragment(album)
                    )
                },
                true
            )
        })
    }
}
