package com.karcz.piotr.ecom.ui.product

import com.karcz.piotr.ecom.data.ProductDomainModel

sealed class ProductViewState {

    data class Success(val productDomainModel: ProductDomainModel?) : ProductViewState()
    object Loading : ProductViewState()
    object Error : ProductViewState()

    companion object {
        val INITIAL = Success(productDomainModel = null)
    }
}
