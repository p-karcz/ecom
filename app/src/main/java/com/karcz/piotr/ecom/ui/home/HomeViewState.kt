package com.karcz.piotr.ecom.ui.home

import com.karcz.piotr.ecom.data.domain.ProductDomainModel

sealed class HomeViewState {

    data class Success(val productDomains: List<ProductDomainModel>) : HomeViewState()
    object Loading : HomeViewState()
    object Error : HomeViewState()

    companion object {
        val INITIAL = Success(productDomains = emptyList())
    }
}
