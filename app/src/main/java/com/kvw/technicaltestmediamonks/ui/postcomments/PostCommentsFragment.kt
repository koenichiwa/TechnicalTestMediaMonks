package com.kvw.technicaltestmediamonks.ui.postcomments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.kvw.technicaltestmediamonks.R
import kotlinx.android.synthetic.main.post_comments_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class PostCommentsFragment : Fragment() {

    private val args: PostCommentsFragmentArgs by navArgs()
    private val postCommentsViewModel: PostCommentsViewModel by viewModel { parametersOf(args.post) }

    companion object {
        fun newInstance() =
            PostCommentsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_comments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        postCommentsViewModel.onLoad()

        postCommentsViewModel.comments.observe(this, Observer {
            recyclerView_postComments_comments.swapAdapter(PostCommentsAdapter(it), true)
        })
    }

}
