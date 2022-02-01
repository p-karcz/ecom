package com.karcz.piotr.ecom.ui.home

import com.karcz.piotr.ecom.data.domain.ProductDomainModel

sealed class HomeViewState {

    data class Success(val productDomains: List<ProductDomainModel>) : HomeViewState()
    data class CachedSuccess(val productDomains: List<ProductDomainModel>) : HomeViewState()
    object Loading : HomeViewState()
    object NetworkError : HomeViewState()
    object Unauthorized : HomeViewState()

    companion object {
        val INITIAL = Success(productDomains = emptyList())
    }
}
