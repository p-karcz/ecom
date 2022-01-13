package com.karcz.piotr.ecom.ui.cart

import com.karcz.piotr.ecom.data.ProductModel

sealed class CartViewState {

    data class Success(val products: List<ProductModel>) : CartViewState()
    object Loading : CartViewState()
    object Error : CartViewState()

    companion object {
        val INITIAL = Success(products = emptyList())
    }
}
