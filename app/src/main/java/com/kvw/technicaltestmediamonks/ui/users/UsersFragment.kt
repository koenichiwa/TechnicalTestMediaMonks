package com.kvw.technicaltestmediamonks.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kvw.technicaltestmediamonks.R
import kotlinx.android.synthetic.main.users_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {

    private val usersViewModel: UsersViewModel by viewModel()

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.users_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        usersViewModel.users.observe(this, Observer { userList ->
            recyclerView_users_users.swapAdapter(UserAdapter(userList) { user ->
                findNavController().navigate(
                    // UsersFragmentDirections.actionUsersFragmentToUserAlbumsFragment(user)
                    UsersFragmentDirections.actionUsersFragmentToUserDetailFragment(user)
                )
            }, true)
        })
    }
}
