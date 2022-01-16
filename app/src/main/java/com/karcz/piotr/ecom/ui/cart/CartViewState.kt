package com.karcz.piotr.ecom.ui.cart

import com.karcz.piotr.ecom.data.domain.ProductDomainModel

sealed class CartViewState {

    data class Success(val productDomains: List<ProductDomainModel>) : CartViewState()
    object Loading : CartViewState()
    object Error : CartViewState()

    companion object {
        val INITIAL = Success(productDomains = emptyList())
    }
}
