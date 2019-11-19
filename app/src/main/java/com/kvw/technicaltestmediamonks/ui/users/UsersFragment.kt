package com.kvw.technicaltestmediamonks.ui.users

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kvw.technicaltestmediamonks.R
import kotlinx.android.synthetic.main.users_fragment.*


class UsersFragment : Fragment() {

    private val viewModel: UsersViewModel by viewModels()

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.users_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.users.observe(this, Observer {
            recyclerView_users_users.swapAdapter(UserAdapter(it) {}, true)
        })
    }
}
