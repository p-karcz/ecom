package com.karcz.piotr.ecom.ui.cart

import com.karcz.piotr.ecom.data.ProductModel

sealed class CartViewState {
    object Loading : CartViewState()
    object Error : CartViewState()
    data class Success(
        val products: List<ProductModel> = emptyList()
    ) : CartViewState()
}
