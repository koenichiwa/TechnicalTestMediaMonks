package com.kvw.technicaltestmediamonks.ui.userposts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import com.kvw.technicaltestmediamonks.R
import com.kvw.technicaltestmediamonks.business.models.UserModel
import com.kvw.technicaltestmediamonks.ui.userdetail.UserDetailViewModel
import kotlinx.android.synthetic.main.user_posts_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class UserPostsFragment(_userDetailViewModel: UserDetailViewModel) : Fragment() {

//    private val args: UserPostsFragmentArgs by navArgs()
//    private val userPostsViewModel: UserPostsViewModel by viewModel { parametersOf(args.user) }

    private val userDetailViewModel: UserDetailViewModel = _userDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_posts_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userDetailViewModel.posts.observe(this, Observer {
            recyclerView_userPosts_post.swapAdapter(UserPostsAdapter(it) {}, true)
        })
    }
}
