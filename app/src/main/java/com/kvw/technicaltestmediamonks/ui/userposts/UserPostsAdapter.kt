package com.kvw.technicaltestmediamonks.ui.userposts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kvw.technicaltestmediamonks.R
import com.kvw.technicaltestmediamonks.business.models.PostModel
import kotlinx.android.synthetic.main.item_userpost_post.view.*

class UserPostsAdapter(
    private val posts: List<PostModel>,
    private val onClick: (PostModel) -> Unit
) : RecyclerView.Adapter<UserPostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_userpost_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind ViewHolder here
        holder.itemView.setOnClickListener { onClick(posts[position]) }
        holder.itemView.textView_itempost_title.text = posts[position].title
        holder.itemView.textView_itempost_body.text = posts[position].body
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}