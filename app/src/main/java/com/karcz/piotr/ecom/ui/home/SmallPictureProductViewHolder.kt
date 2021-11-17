package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.data.domain.ProductModel
import com.karcz.piotr.ecom.databinding.SmallPictureProductBinding

class SmallPictureProductViewHolder(private val binding: SmallPictureProductBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun onBind(item: ProductModel.SmallPicture) = with(binding) {
        productImageView.background = item.image
        productTextView.text = item.text
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): SmallPictureProductViewHolder {
            return SmallPictureProductViewHolder(
                SmallPictureProductBinding.inflate(inflater, parent, false)
            )
        }
    }
}