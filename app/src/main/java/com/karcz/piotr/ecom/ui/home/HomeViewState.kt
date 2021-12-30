package com.karcz.piotr.ecom.ui.home

import com.karcz.piotr.ecom.data.CategoryModel
import com.karcz.piotr.ecom.data.ProductModel

sealed class HomeViewState {
    object Loading : HomeViewState()
    object Error : HomeViewState()
    data class Success(
        val products: List<ProductModel> = emptyList(),
        val categories: List<CategoryModel> = emptyList()
    ) : HomeViewState()
}
