package com.karcz.piotr.ecom.ui.home

sealed class HomeInteraction {
    object SeeAllProductsCategoryClicked : HomeInteraction()
    object SeeAllProductsClicked : HomeInteraction()
    data class CategoryTitleClicked(val category: Pair<String, Boolean>) : HomeInteraction()
    data class ProductClicked(val id: Int) : HomeInteraction()
}
