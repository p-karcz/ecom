package com.karcz.piotr.ecom.ui.home

import com.karcz.piotr.ecom.data.ProductModel

sealed class HomeViewState {

    data class Success(val products: List<ProductModel>) : HomeViewState()
    object Loading : HomeViewState()
    object Error : HomeViewState()

    companion object {
        val INITIAL = Success(products = emptyList())
    }
}
