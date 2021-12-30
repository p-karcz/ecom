package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karcz.piotr.ecom.data.ProductModel
import com.karcz.piotr.ecom.databinding.ListCategoryProductBinding

class CategoryProductViewHolder(private val binding: ListCategoryProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(productModel: ProductModel) {
        with(binding) {
            Glide.with(root.context)
                .load(productModel.image)
                .into(categoryProductImageView)

            categoryProductTitleTextView.text = productModel.name
            categoryProductDescriptionTextView.text = productModel.description
            categoryProductPriceTextView.text = productModel.price.toString()
        }
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): CategoryProductViewHolder {
            return CategoryProductViewHolder(ListCategoryProductBinding.inflate(inflater, parent, false))
        }
    }
}
