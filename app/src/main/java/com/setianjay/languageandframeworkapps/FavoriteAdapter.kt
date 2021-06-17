package com.setianjay.languageandframeworkapps

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.setianjay.languageandframeworkapps.database.entity.ContentEntity
import com.setianjay.languageandframeworkapps.databinding.ItemFavoriteBinding


class FavoriteAdapter(private val favorites: ArrayList<ContentEntity>, private val listeners: OnAdapterListener): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    interface OnAdapterListener{
        fun onClick(data: ContentEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = favorites[position]
        holder.bind(content, listeners)
    }

    inner class ViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceType", "SetTextI18n")
        fun bind(data: ContentEntity, listeners: OnAdapterListener){
            binding.tvFavorite.text = data.title
            when(data.type) {
                "languages" -> binding.tvFavoriteType.text = "Programming Language"
                else -> binding.tvFavoriteType.text = "Programming Framework"
            }
            Glide.with(binding.ivFavorite.context)
                .load(data.poster)
                .into(binding.ivFavorite)

            binding.containerContentFavorite.setOnClickListener {
                listeners.onClick(data)
            }
        }
    }

    fun setData(data: List<ContentEntity>){
        favorites.clear()
        favorites.addAll(data)
        notifyDataSetChanged()
    }
}