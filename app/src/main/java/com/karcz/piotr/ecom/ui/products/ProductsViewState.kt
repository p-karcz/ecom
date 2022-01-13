package com.karcz.piotr.ecom.ui.products

import com.karcz.piotr.ecom.data.ProductModel

sealed class ProductsViewState {

    data class Success(val products: List<ProductModel>) : ProductsViewState()
    object Loading : ProductsViewState()
    object Error : ProductsViewState()

    companion object {
        val INITIAL = Success(products = emptyList())
    }
}
