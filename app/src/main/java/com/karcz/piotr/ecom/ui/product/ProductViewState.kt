package com.karcz.piotr.ecom.ui.product

import com.karcz.piotr.ecom.data.ProductModel

sealed class ProductViewState {
    object Loading : ProductViewState()
    object Error : ProductViewState()
    data class Success(
        val productModel: ProductModel? = null
    ) : ProductViewState()
}
