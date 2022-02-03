package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.data.domain.ProductDomainModel
import com.karcz.piotr.ecom.databinding.ListHomeProductItemBinding

class ProductViewHolder(private val binding: ListHomeProductItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: ProductDomainModel) = with(binding) {
        productTitleTextView.text = data.name
        productDescriptionTextView.text = data.description
        productPriceTextView.text = binding.root.context.getString(R.string.price, data.price)
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): ProductViewHolder {
            return ProductViewHolder(
                ListHomeProductItemBinding.inflate(inflater, parent, false)
            )
        }
    }
}
