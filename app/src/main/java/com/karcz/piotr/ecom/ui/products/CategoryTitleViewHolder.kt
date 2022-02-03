package com.karcz.piotr.ecom.ui.products

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.databinding.ListProductsCategoryTitleItemBinding

class CategoryTitleViewHolder(private val binding: ListProductsCategoryTitleItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: Pair<String, Boolean>, onClick: (Pair<String, Boolean>) -> Unit) =
        with(binding) {
            categoryProductTitleTextView.text = data.first
            if (data.second) {
                categoryProductTitleContainer.background = AppCompatResources.getDrawable(
                    binding.root.context,
                    R.drawable.products_active_category_title_background
                )
                categoryProductTitleTextView.typeface = Typeface.DEFAULT_BOLD
            } else {
                categoryProductTitleContainer.background = AppCompatResources.getDrawable(
                    binding.root.context,
                    R.drawable.white_rounded_background
                )
                categoryProductTitleTextView.typeface = Typeface.DEFAULT
            }
            root.setOnClickListener { onClick(data) }
        }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): CategoryTitleViewHolder {
            return CategoryTitleViewHolder(
                ListProductsCategoryTitleItemBinding.inflate(inflater, parent, false)
            )
        }
    }
}
