package com.kvw.technicaltestmediamonks.ui.albumphotos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kvw.technicaltestmediamonks.R
import com.kvw.technicaltestmediamonks.business.models.PhotoModel
import com.kvw.technicaltestmediamonks.data.retrofit.models.Photo
import kotlinx.android.synthetic.main.item_albumphotos_photo.view.*

class AlbumPhotosAdapter(private val photos: List<PhotoModel>) :
    RecyclerView.Adapter<AlbumPhotosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_albumphotos_photo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView){
            textView_itemPhoto_title.text = photos[position].title

            Glide.with(this)
                .load(photos[position].url)
                .into(imageView_itemPhoto_photo)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}