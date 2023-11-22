package com.andreirookie.hotel_task.ui.hotel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andreirookie.hotel_task.databinding.HotelPhotoItemBinding
import com.bumptech.glide.Glide

class HotelPhotoSliderAdapter
: ListAdapter<String, HotelPhotoViewHolder>(HotelPhotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelPhotoViewHolder {
        val binding = HotelPhotoItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HotelPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelPhotoViewHolder, position: Int) {
        val photoUrl = getItem(position)
        holder.bind(photoUrl)
    }

}

class HotelPhotoViewHolder(
    private val binding: HotelPhotoItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photoUrl: String) {
        Glide.with(binding.root.context)
            .load(photoUrl)
            .into(binding.hotelPhoto)
    }
}

class HotelPhotoDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}