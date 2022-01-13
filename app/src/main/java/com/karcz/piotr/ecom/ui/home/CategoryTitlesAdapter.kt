package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class CategoryTitlesAdapter : ListAdapter<String, CategoryTitleViewHolder>(CategoryDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryTitleViewHolder {
        return CategoryTitleViewHolder.inflate(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: CategoryTitleViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    object CategoryDiffer : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String) = true

        override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
    }
}
