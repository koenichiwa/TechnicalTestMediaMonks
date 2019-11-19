package com.kvw.technicaltestmediamonks.ui.user_albums

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.kvw.technicaltestmediamonks.R
import kotlinx.android.synthetic.main.user_albums_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class UserAlbumsFragment : Fragment() {
    private val args: UserAlbumsFragmentArgs by navArgs()
    private val userAlbumsViewModel: UserAlbumsViewModel by viewModel { parametersOf(args.user) }

    companion object {
        fun newInstance() = UserAlbumsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_albums_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userAlbumsViewModel.albums.observe(this, Observer { albumList ->
            recyclerView_userAlbums_albums.swapAdapter(
                UserAlbumsAdapter(albumList) {},
                true
            )
        })
    }

}
