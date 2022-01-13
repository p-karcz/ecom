package com.karcz.piotr.ecom.ui.products

import com.karcz.piotr.ecom.data.ProductDomainModel

sealed class ProductsViewState {

    data class Success(val productDomains: List<ProductDomainModel>) : ProductsViewState()
    object Loading : ProductsViewState()
    object Error : ProductsViewState()

    companion object {
        val INITIAL = Success(productDomains = emptyList())
    }
}
