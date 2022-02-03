package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.karcz.piotr.ecom.data.domain.ProductDomainModel

class CategoryProductsAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<ProductDomainModel, CategoryProductViewHolder>(
        CategoryProductsDiffer
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductViewHolder {
        return CategoryProductViewHolder.inflate(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: CategoryProductViewHolder, position: Int) {
        holder.onBind(getItem(position), onClick)
    }

    object CategoryProductsDiffer : DiffUtil.ItemCallback<ProductDomainModel>() {
        override fun areItemsTheSame(oldItem: ProductDomainModel, newItem: ProductDomainModel) =
            oldItem::class == newItem::class

        override fun areContentsTheSame(oldItem: ProductDomainModel, newItem: ProductDomainModel) =
            oldItem == newItem
    }
}
