package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.data.domain.HomeListItem
import com.karcz.piotr.ecom.databinding.ListHomeHeaderBinding

class HeaderViewHolder(private val binding: ListHomeHeaderBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: HomeListItem.Header) {
        binding.headerTextView.text = data.text
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): HeaderViewHolder {
            return HeaderViewHolder(ListHomeHeaderBinding.inflate(inflater, parent, false))
        }
    }
}
