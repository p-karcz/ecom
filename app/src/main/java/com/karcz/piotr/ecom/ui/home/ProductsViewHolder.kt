package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.data.domain.HomeListItem
import com.karcz.piotr.ecom.databinding.ListHomeProductsBinding

class ProductsViewHolder(private val binding: ListHomeProductsBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: HomeListItem.Products) = with(binding) {

    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): ProductsViewHolder {
            return ProductsViewHolder(
                ListHomeProductsBinding.inflate(inflater, parent, false)
            )
        }
    }
}
