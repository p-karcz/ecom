package com.karcz.piotr.ecom.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karcz.piotr.ecom.data.domain.ProductDomainModel
import com.karcz.piotr.ecom.databinding.ListProductBinding

class ProductViewHolder(private val binding: ListProductBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun onBind(productDomainModel: ProductDomainModel) = with(binding) {
        Glide.with(root.context)
            .load(productDomainModel.image)
            .into(productImageView)

        productTitleTextView.text = productDomainModel.name
        productDescriptionTextView.text = productDomainModel.description
        productPriceTextView.text = productDomainModel.price.toString()
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): ProductViewHolder {
            return ProductViewHolder(
                ListProductBinding.inflate(inflater, parent, false)
            )
        }
    }
}
