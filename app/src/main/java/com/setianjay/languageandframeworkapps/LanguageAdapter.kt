package com.setianjay.languageandframeworkapps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.setianjay.languageandframeworkapps.databinding.ItemListBinding

class LanguageAdapter(private val languages: ArrayList<LanguageAndFrameworkModel>) :
    RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = languages.size

    override fun onBindViewHolder(holder: LanguageAdapter.ViewHolder, position: Int) {
        val items = languages[position]
        holder.bind(items)
    }

    inner class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: LanguageAndFrameworkModel){
            Glide.with(binding.ivLanguage.context)
                .load(data.poster)
                .into(binding.ivLanguage)
        }
    }

    fun setData(data: MutableList<LanguageAndFrameworkModel>){
        languages.clear()
        languages.addAll(data)
        notifyDataSetChanged()
    }
}