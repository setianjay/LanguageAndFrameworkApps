package com.setianjay.languageandframeworkapps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.setianjay.languageandframeworkapps.databinding.ItemListBinding

class FrameworkAdapter(private val frameworks: ArrayList<LanguageAndFrameworkModel>,private val listener: OnAdapterListener): RecyclerView.Adapter<FrameworkAdapter.ViewHolder>() {

    interface OnAdapterListener{
        fun onClick(data: LanguageAndFrameworkModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ItemListBinding.inflate(
        LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount(): Int = frameworks.size

    override fun onBindViewHolder(holder: FrameworkAdapter.ViewHolder, position: Int) {
        val items = frameworks[position]
        holder.bind(items,listener)
    }

    inner class ViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: LanguageAndFrameworkModel, listener: OnAdapterListener){
            Glide.with(binding.ivLanguage.context)
                .load(data.poster)
                .into(binding.ivLanguage)

            binding.ivLanguage.setOnClickListener {
                listener.onClick(data)
            }
        }
    }
}