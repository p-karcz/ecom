package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.data.domain.ProductDomainModel
import com.karcz.piotr.ecom.databinding.ListHomeProductBinding

class ProductViewHolder(private val binding: ListHomeProductBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun onBind(productDomainModel: ProductDomainModel) = with(binding) {
        productTitleTextView.text = productDomainModel.name
        productDescriptionTextView.text = productDomainModel.description
        productPriceTextView.text = productDomainModel.price.toString()
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): ProductViewHolder {
            return ProductViewHolder(
                ListHomeProductBinding.inflate(inflater, parent, false)
            )
        }
    }
}
