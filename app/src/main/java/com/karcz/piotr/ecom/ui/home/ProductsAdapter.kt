package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.data.domain.ProductModel

class ProductsAdapter : ListAdapter<ProductModel, RecyclerView.ViewHolder>(ProductsDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            SMALL_PICTURE -> SmallPictureProductViewHolder.inflate(inflater, parent)
            BIG_PICTURE -> BigPictureProductViewHolder.inflate(inflater, parent)
            else -> throw IllegalStateException("Unexpected list item type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when (holder) {
        is SmallPictureProductViewHolder -> holder.onBind(getItem(position) as ProductModel.SmallPicture)
        is BigPictureProductViewHolder -> holder.onBind(getItem(position) as ProductModel.BigPicture)
        else -> throw IllegalStateException("Unexpected ViewHolder type")
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is ProductModel.SmallPicture -> SMALL_PICTURE
        is ProductModel.BigPicture -> BIG_PICTURE
    }

    companion object {
        private const val SMALL_PICTURE = 0
        private const val BIG_PICTURE = 1
    }
}

object ProductsDiffer : DiffUtil.ItemCallback<ProductModel>() {
    override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel) =
        oldItem::class == newItem::class

    override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel) =
        oldItem == newItem
}