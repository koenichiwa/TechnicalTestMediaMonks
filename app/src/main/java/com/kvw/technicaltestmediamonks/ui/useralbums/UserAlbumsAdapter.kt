package com.kvw.technicaltestmediamonks.ui.useralbums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kvw.technicaltestmediamonks.R
import com.kvw.technicaltestmediamonks.business.models.AlbumModel
import kotlinx.android.synthetic.main.item_useralbum_album.view.*

class UserAlbumsAdapter(
    private val albums: List<AlbumModel>,
    private val onClick: (AlbumModel) -> Unit
) : RecyclerView.Adapter<UserAlbumsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_useralbum_album, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = albums.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            setOnClickListener { onClick(albums[position]) }
            textView_userAlbumItem_title.text = albums[position].title
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}