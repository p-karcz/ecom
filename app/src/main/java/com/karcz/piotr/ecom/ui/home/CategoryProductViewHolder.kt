package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.data.domain.ProductDomainModel
import com.karcz.piotr.ecom.databinding.ListHomeCategoryProductItemBinding

class CategoryProductViewHolder(private val binding: ListHomeCategoryProductItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: ProductDomainModel) = with(binding) {
        categoryProductTitleTextView.text = data.category
        categoryProductPriceTextView.text = binding.root.context.getString(R.string.price, data.price)
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): CategoryProductViewHolder {
            return CategoryProductViewHolder(
                ListHomeCategoryProductItemBinding.inflate(inflater, parent, false)
            )
        }
    }
}
