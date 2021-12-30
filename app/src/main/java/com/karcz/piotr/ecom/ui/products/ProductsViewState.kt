package com.karcz.piotr.ecom.ui.products

import com.karcz.piotr.ecom.data.ProductModel

sealed class ProductsViewState {
    object Loading : ProductsViewState()
    object Error : ProductsViewState()
    data class Success(
        val products: List<ProductModel> = emptyList()
    ) : ProductsViewState()
}
