package com.karcz.piotr.ecom.ui.products

import com.karcz.piotr.ecom.data.domain.ProductDomainModel

sealed class ProductsViewState {

    data class Success(val products: List<ProductDomainModel>) : ProductsViewState()
    object Loading : ProductsViewState()
    object Error : ProductsViewState()

    companion object {
        val INITIAL = Success(products = emptyList())
    }
}
