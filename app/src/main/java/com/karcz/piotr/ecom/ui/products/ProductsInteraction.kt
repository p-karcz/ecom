package com.karcz.piotr.ecom.ui.products

sealed class ProductsInteraction {
    data class ArgumentPassed(val category: String?) : ProductsInteraction()
    data class CategoryTitleClicked(val category: Pair<String, Boolean>) : ProductsInteraction()
    data class ProductClicked(val id: Int) : ProductsInteraction()
}
