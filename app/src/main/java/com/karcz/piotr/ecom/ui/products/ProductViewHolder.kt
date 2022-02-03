package com.karcz.piotr.ecom.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.data.domain.ProductDomainModel
import com.karcz.piotr.ecom.databinding.ListProductItemBinding

class ProductViewHolder(private val binding: ListProductItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: ProductDomainModel, onClick: (Int) -> Unit) = with(binding) {
        productTitleTextView.text = data.name
        productDescriptionTextView.text = binding.root.context.getString(R.string.short_text, data.description.substring(0, 25))
        productPriceTextView.text = binding.root.context.getString(R.string.price, data.price.toString())
        root.setOnClickListener { onClick(data.id) }
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): ProductViewHolder {
            return ProductViewHolder(
                ListProductItemBinding.inflate(inflater, parent, false)
            )
        }
    }
}
