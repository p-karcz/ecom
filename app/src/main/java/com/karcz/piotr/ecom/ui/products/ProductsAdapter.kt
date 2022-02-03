package com.karcz.piotr.ecom.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.karcz.piotr.ecom.data.domain.ProductDomainModel

class ProductsAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<ProductDomainModel, ProductViewHolder>(ProductsDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.inflate(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(getItem(position), onClick)
    }

    object ProductsDiffer : DiffUtil.ItemCallback<ProductDomainModel>() {
        override fun areItemsTheSame(oldItem: ProductDomainModel, newItem: ProductDomainModel) =
            oldItem::class == newItem::class

        override fun areContentsTheSame(oldItem: ProductDomainModel, newItem: ProductDomainModel) =
            oldItem == newItem
    }
}
