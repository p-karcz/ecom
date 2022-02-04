package com.karcz.piotr.ecom.ui.products

sealed class ProductsInteraction {
    data class CategoryTitleClicked(val category: Pair<String, Boolean>) : ProductsInteraction()
    data class ProductClicked(val id: Int) : ProductsInteraction()
}
