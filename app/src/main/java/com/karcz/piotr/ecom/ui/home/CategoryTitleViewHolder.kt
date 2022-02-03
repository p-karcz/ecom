package com.karcz.piotr.ecom.ui.home

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.databinding.ListHomeCategoryTitleItemBinding

class CategoryTitleViewHolder(private val binding: ListHomeCategoryTitleItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: Pair<String, Boolean>, onClick: (Pair<String, Boolean>) -> Unit) = with(binding) {
        categoryProductTitleTextView.text = data.first
        if (data.second) {
            categoryProductTitleTextView.typeface = Typeface.DEFAULT_BOLD
            categoryActiveContainerView.visibility = View.VISIBLE
        } else {
            categoryProductTitleTextView.typeface = Typeface.DEFAULT
            categoryActiveContainerView.visibility = View.GONE
        }
        root.setOnClickListener { onClick(data) }
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): CategoryTitleViewHolder {
            return CategoryTitleViewHolder(
                ListHomeCategoryTitleItemBinding.inflate(inflater, parent, false)
            )
        }
    }
}
