package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.databinding.ListCategoryTitleBinding

class CategoryTitleViewHolder(private val binding: ListCategoryTitleBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(category: String) {
        binding.categoryProductTitleTextView.text = category
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): CategoryTitleViewHolder {
            return CategoryTitleViewHolder(ListCategoryTitleBinding.inflate(inflater, parent, false))
        }
    }
}
