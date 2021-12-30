package com.karcz.piotr.ecom.common.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.karcz.piotr.ecom.data.ProductModel

class ProductsAdapter : ListAdapter<ProductModel, ProductViewHolder>(ProductsDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.inflate(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    object ProductsDiffer : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel) =
            oldItem::class == newItem::class

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel) =
            oldItem == newItem
    }
}
