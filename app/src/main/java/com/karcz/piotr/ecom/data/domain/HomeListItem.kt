package com.karcz.piotr.ecom.data.domain

sealed class HomeListItem {
    object Search : HomeListItem()
    data class Header(val text: String) : HomeListItem()
    data class CategoriesTitles(val categoryHeaders: List<String>) : HomeListItem()
    data class CategoryProducts(val categoryItems: List<ProductDomainModel>) : HomeListItem()
    data class Products(val products: List<ProductDomainModel>) : HomeListItem()
}
