package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.data.domain.HomeListItem
import com.karcz.piotr.ecom.databinding.ListHomeCategoryProductsBinding

class CategoryProductsViewHolder(private val binding: ListHomeCategoryProductsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: HomeListItem.CategoryProducts) {
        with(binding) {

        }
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): CategoryProductsViewHolder {
            return CategoryProductsViewHolder(ListHomeCategoryProductsBinding.inflate(inflater, parent, false))
        }
    }
}
