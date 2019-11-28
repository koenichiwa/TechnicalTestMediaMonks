package com.kvw.technicaltestmediamonks.ui.userposts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.kvw.technicaltestmediamonks.R
import kotlinx.android.synthetic.main.user_posts_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class UserPostsFragment : Fragment() {

    private val args: UserPostsFragmentArgs by navArgs()
    private val userPostsViewModel: UserPostsViewModel by viewModel { parametersOf(args.user) }

    companion object {
        fun newInstance() =
            UserPostsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_posts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userPostsViewModel.posts.observe(this, Observer {
            recyclerView_userPosts_post.swapAdapter(UserPostsAdapter(it) {}, true)
        })
    }

}
