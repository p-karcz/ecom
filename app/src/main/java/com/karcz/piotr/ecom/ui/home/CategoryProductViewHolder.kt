package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karcz.piotr.ecom.data.domain.ProductDomainModel
import com.karcz.piotr.ecom.databinding.ListCategoryProductBinding

class CategoryProductViewHolder(private val binding: ListCategoryProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(productDomainModel: ProductDomainModel) {
        with(binding) {
            Glide.with(root.context)
                .load(productDomainModel.image)
                .into(categoryProductImageView)

            categoryProductTitleTextView.text = productDomainModel.name
            categoryProductDescriptionTextView.text = productDomainModel.description
            categoryProductPriceTextView.text = productDomainModel.price.toString()
        }
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): CategoryProductViewHolder {
            return CategoryProductViewHolder(ListCategoryProductBinding.inflate(inflater, parent, false))
        }
    }
}
