package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.data.domain.HomeListItem
import com.karcz.piotr.ecom.databinding.ListHomeCategoriesTitlesBinding
import com.karcz.piotr.ecom.databinding.ListHomeCategoryTitleBinding

class CategoriesTitlesViewHolder(private val binding: ListHomeCategoriesTitlesBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: HomeListItem.CategoriesTitles) {

    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): CategoriesTitlesViewHolder {
            return CategoriesTitlesViewHolder(ListHomeCategoriesTitlesBinding.inflate(inflater, parent, false))
        }
    }
}
