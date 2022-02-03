package com.karcz.piotr.ecom.ui.home

sealed class HomeEvent {
    object InternetError : HomeEvent()
    data class NavigateToProductsFragment(val category: String) : HomeEvent()
    data class NavigateToProductFragment(val id: Int) : HomeEvent()
}
