package com.kvw.technicaltestmediamonks.ui.postcomments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kvw.technicaltestmediamonks.R
import com.kvw.technicaltestmediamonks.business.models.CommentModel
import kotlinx.android.synthetic.main.item_postcomments_comment.view.*

class PostCommentsAdapter(private val comments: List<CommentModel>) :
    RecyclerView.Adapter<PostCommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_postcomments_comment, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind ViewHolder here
        holder.itemView.textView_itemcomment_body.text = comments[position].body
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}