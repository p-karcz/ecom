package com.karcz.piotr.ecom.ui.product

sealed class ProductEvent {
    object ProductAddedSuccessfully : ProductEvent()
    object AuthorizationRequired : ProductEvent()
    object AddToCartNetworkError : ProductEvent()
    object LoadingProductNetworkError : ProductEvent()
}
