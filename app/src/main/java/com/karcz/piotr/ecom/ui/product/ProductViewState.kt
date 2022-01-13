package com.karcz.piotr.ecom.ui.product

import com.karcz.piotr.ecom.data.ProductModel

sealed class ProductViewState {

    data class Success(val productModel: ProductModel?) : ProductViewState()
    object Loading : ProductViewState()
    object Error : ProductViewState()

    companion object {
        val INITIAL = Success(productModel = null)
    }
}
