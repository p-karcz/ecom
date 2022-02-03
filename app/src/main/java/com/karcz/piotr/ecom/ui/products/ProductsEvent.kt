package com.karcz.piotr.ecom.ui.products

sealed class ProductsEvent {
    object InternetError : ProductsEvent()
    data class NavigateToProductFragment(val id: Int) : ProductsEvent()
}
