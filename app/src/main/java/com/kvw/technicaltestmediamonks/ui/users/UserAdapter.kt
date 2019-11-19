package com.kvw.technicaltestmediamonks.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kvw.technicaltestmediamonks.R
import com.kvw.technicaltestmediamonks.models.User
import kotlinx.android.synthetic.main.item_users_user.view.*

class UserAdapter(
    private val users: List<User>,
    private val onClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_users_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView){
            setOnClickListener { onClick(users[position]) }
            textView_userItem_name.text = users[position].name
            textView_userItem_username.text = users[position].username
            textView_userItem_email.text = users[position].email
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}