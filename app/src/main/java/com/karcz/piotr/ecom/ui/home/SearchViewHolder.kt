package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.databinding.ListHomeSearchBinding

class SearchViewHolder(binding: ListHomeSearchBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): SearchViewHolder {
            return SearchViewHolder(ListHomeSearchBinding.inflate(inflater, parent, false))
        }
    }
}
