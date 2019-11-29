package com.kvw.technicaltestmediamonks.ui.userdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.kvw.technicaltestmediamonks.R
import com.kvw.technicaltestmediamonks.ui.useralbums.UserAlbumsFragment
import com.kvw.technicaltestmediamonks.ui.userposts.UserPostsFragment
import kotlinx.android.synthetic.main.user_detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
class UserDetailFragment : Fragment() {

    private val args: UserDetailFragmentArgs by navArgs()

    private val userDetailViewModel: UserDetailViewModel by viewModel { parametersOf(args.user) }

    companion object {
        fun newInstance() =
            UserDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userDetailViewModel.onLoad()

        setUpBottomNav()
    }

    private fun setUpBottomNav() {
        switchFragment(UserAlbumsFragment(userDetailViewModel)) // TODO no argument passing, use sharedViewModel

        bottomNavigation_userdetail.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuItem_userDetail_posts -> {
                    switchFragment(UserPostsFragment(userDetailViewModel))
                    true
                }
                R.id.menuItem_userDetail_albums -> {
                    switchFragment(UserAlbumsFragment(userDetailViewModel))
                    true
                }
                else -> { false }
            }
        }
    }

    private fun switchFragment(fragment: Fragment) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(frameLayout_userdetail_fragment.id, fragment)
            ?.commit()
    }
}
