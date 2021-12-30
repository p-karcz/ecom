package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.karcz.piotr.ecom.data.CategoryModel

class CategoryTitlesAdapter : ListAdapter<CategoryModel, CategoryTitleViewHolder>(CategoryDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryTitleViewHolder {
        return CategoryTitleViewHolder.inflate(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: CategoryTitleViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    object CategoryDiffer : DiffUtil.ItemCallback<CategoryModel>() {
        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel) =
            oldItem::class == newItem::class

        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel) =
            oldItem == newItem
    }
}
