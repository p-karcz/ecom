package com.karcz.piotr.ecom.ui.home

import com.karcz.piotr.ecom.data.domain.ProductDomainModel

sealed class HomeViewState(
    val products: List<ProductDomainModel>,
    val categories: List<Pair<String, Boolean>>,
    val categoryProducts: List<ProductDomainModel>
) {

    class Success(
        products: List<ProductDomainModel>,
        categories: List<Pair<String, Boolean>>,
        categoryProducts: List<ProductDomainModel>
    ) :
        HomeViewState(products, categories, categoryProducts)

    object Loading : HomeViewState(emptyList(), emptyList(), emptyList())
    class NetworkError(
        products: List<ProductDomainModel>,
        categories: List<Pair<String, Boolean>>,
        categoryProducts: List<ProductDomainModel>
    ) :
        HomeViewState(products, categories, categoryProducts)

    companion object {
        val INITIAL = Loading
    }
}
