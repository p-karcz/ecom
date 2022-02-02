package com.karcz.piotr.ecom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karcz.piotr.ecom.data.domain.HomeListItem

class HomeAdapter : ListAdapter<HomeListItem, RecyclerView.ViewHolder>(HomeListItemDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            SEARCH -> SearchViewHolder.inflate(layoutInflater, parent)
            HEADER -> HeaderViewHolder.inflate(layoutInflater, parent)
            CATEGORIES_TITLES -> CategoriesTitlesViewHolder.inflate(layoutInflater, parent)
            CATEGORY_PRODUCTS -> CategoryProductsViewHolder.inflate(layoutInflater, parent)
            PRODUCTS -> ProductsViewHolder.inflate(layoutInflater, parent)
            else -> throw IllegalStateException("Unexpected list item type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        when(holder) {
            is HeaderViewHolder -> holder.onBind(data as HomeListItem.Header)
            is CategoriesTitlesViewHolder -> holder.onBind(data as HomeListItem.CategoriesTitles)
            is CategoryProductsViewHolder -> holder.onBind(data as HomeListItem.CategoryProducts)
            is ProductsViewHolder -> holder.onBind(data as HomeListItem.Products)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(currentList[position]) {
            is HomeListItem.Search -> SEARCH
            is HomeListItem.Header -> HEADER
            is HomeListItem.CategoriesTitles -> CATEGORIES_TITLES
            is HomeListItem.CategoryProducts -> CATEGORY_PRODUCTS
            is HomeListItem.Products -> PRODUCTS
        }
    }

    object HomeListItemDiffer : DiffUtil.ItemCallback<HomeListItem>() {
        override fun areItemsTheSame(oldItem: HomeListItem, newItem: HomeListItem) =
            oldItem::class == newItem::class

        override fun areContentsTheSame(oldItem: HomeListItem, newItem: HomeListItem) =
            oldItem == newItem
    }

    companion object {
        const val SEARCH = 0
        const val HEADER = 1
        const val CATEGORIES_TITLES = 2
        const val CATEGORY_PRODUCTS = 3
        const val PRODUCTS = 4
    }
}
