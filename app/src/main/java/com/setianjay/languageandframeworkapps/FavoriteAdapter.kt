package com.setianjay.languageandframeworkapps

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.setianjay.languageandframeworkapps.database.entity.ContentEntity
import com.setianjay.languageandframeworkapps.databinding.ItemFavoriteBinding
import java.util.*
import kotlin.collections.ArrayList


class FavoriteAdapter(
    private val favorites: ArrayList<ContentEntity>,
    private val listeners: OnAdapterListener
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(), Filterable {

    private var favoriteListFilter = ArrayList<ContentEntity>()

    init {
        favoriteListFilter = favorites
    }

    interface OnAdapterListener {
        fun onClick(data: ContentEntity)
        fun onLongClick(data: ContentEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = favoriteListFilter.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = favoriteListFilter[position]
        holder.bind(content, listeners)
    }

    inner class ViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceType", "SetTextI18n")
        fun bind(data: ContentEntity, listeners: OnAdapterListener) {
            binding.tvFavorite.text = data.title
            when (data.type) {
                "languages" -> binding.tvFavoriteType.text = "Programming Language"
                else -> binding.tvFavoriteType.text = "Programming Framework"
            }
            Glide.with(binding.ivFavorite.context)
                .load(data.poster)
                .into(binding.ivFavorite)

            binding.containerContentFavorite.setOnClickListener {
                listeners.onClick(data)
            }

            binding.containerContentFavorite.setOnLongClickListener {
                listeners.onLongClick(data)
                true
            }
        }
    }

    fun setData(data: List<ContentEntity>) {
        favorites.clear()
        favorites.addAll(data)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                // To get every input in search
                val charSearch = constraint.toString()

                val favoriteFiltered = ArrayList<ContentEntity>()
                for (favorite in favorites) {
                    // if content title contains in char search do it
                    if (favorite.title.toLowerCase(Locale.getDefault())
                            .contains(charSearch.toLowerCase(Locale.getDefault()))
                    ) {
                        // hold the result in favoriteFiltered
                        favoriteFiltered.add(favorite)
                    }
                }

                // sign value for favoriteListFilter from favoriteFiltered
                favoriteListFilter = favoriteFiltered

                // create properties for the result
                val favoritesFilteredResult = FilterResults()
                favoritesFilteredResult.values = favoriteListFilter
                return favoritesFilteredResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                favoriteListFilter = results?.values as ArrayList<ContentEntity>
                notifyDataSetChanged()
            }

        }
    }
}