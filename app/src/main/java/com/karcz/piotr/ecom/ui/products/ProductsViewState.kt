package com.karcz.piotr.ecom.ui.products

import com.karcz.piotr.ecom.data.domain.ProductDomainModel

sealed class ProductsViewState(
    val products: List<ProductDomainModel>,
    val categories: List<Pair<String, Boolean>>,
    val categoryProducts: List<ProductDomainModel>
) {

    class Success(
        products: List<ProductDomainModel>,
        categories: List<Pair<String, Boolean>>,
        categoryProducts: List<ProductDomainModel>
    ) :
        ProductsViewState(products, categories, categoryProducts)

    object Loading : ProductsViewState(emptyList(), emptyList(), emptyList())
    class NetworkError(
        products: List<ProductDomainModel>,
        categories: List<Pair<String, Boolean>>,
        categoryProducts: List<ProductDomainModel>
    ) :
        ProductsViewState(products, categories, categoryProducts)

    companion object {
        val INITIAL = Loading
    }
}
