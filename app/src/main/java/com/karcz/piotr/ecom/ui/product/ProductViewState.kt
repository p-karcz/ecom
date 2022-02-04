package com.karcz.piotr.ecom.ui.product

import com.karcz.piotr.ecom.data.domain.ProductDomainModel

sealed class ProductViewState(val product: ProductDomainModel?) {

    class Success(product: ProductDomainModel) : ProductViewState(product = product)
    object Loading : ProductViewState(null)
    class NetworkError(product: ProductDomainModel?) : ProductViewState(product = product)

    companion object {
        val INITIAL = Loading
    }
}
