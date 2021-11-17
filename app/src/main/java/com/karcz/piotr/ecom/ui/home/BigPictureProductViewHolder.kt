package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.data.domain.ProductModel
import com.karcz.piotr.ecom.databinding.BigPictureProductBinding

class BigPictureProductViewHolder(private val binding: BigPictureProductBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: ProductModel.BigPicture) = with(binding) {
        productImageView.background = item.image
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): BigPictureProductViewHolder {
            return BigPictureProductViewHolder(
                BigPictureProductBinding.inflate(inflater, parent, false)
            )
        }
    }
}