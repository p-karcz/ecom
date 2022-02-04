package com.karcz.piotr.ecom.ui.product

sealed class ProductInteraction {
    object BuyButtonClicked : ProductInteraction()
}
