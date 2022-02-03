package com.karcz.piotr.ecom.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class CategoriesTitlesAdapter(private val onClick: (Pair<String, Boolean>) -> Unit) :
    ListAdapter<Pair<String, Boolean>, CategoryTitleViewHolder>(CategoryDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryTitleViewHolder {
        return CategoryTitleViewHolder.inflate(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: CategoryTitleViewHolder, position: Int) {
        holder.onBind(getItem(position), onClick)
    }

    object CategoryDiffer : DiffUtil.ItemCallback<Pair<String, Boolean>>() {

        override fun areItemsTheSame(
            oldItem: Pair<String, Boolean>,
            newItem: Pair<String, Boolean>
        ) = true

        override fun areContentsTheSame(
            oldItem: Pair<String, Boolean>,
            newItem: Pair<String, Boolean>
        ) = oldItem == newItem
    }
}
