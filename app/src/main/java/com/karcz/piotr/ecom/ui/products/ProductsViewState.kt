package com.karcz.piotr.ecom.ui.products

import com.karcz.piotr.ecom.data.domain.ProductDomainModel

sealed class ProductsViewState(
    val products: List<ProductDomainModel>,
    val categories: List<Pair<String, Boolean>>,
) {

    class Success(
        products: List<ProductDomainModel>,
        categories: List<Pair<String, Boolean>>,
    ) :
        ProductsViewState(products, categories)

    object Loading : ProductsViewState(emptyList(), emptyList())
    class NetworkError(
        products: List<ProductDomainModel>,
        categories: List<Pair<String, Boolean>>,
    ) :
        ProductsViewState(products, categories)

    companion object {
        val INITIAL = Loading
    }
}
