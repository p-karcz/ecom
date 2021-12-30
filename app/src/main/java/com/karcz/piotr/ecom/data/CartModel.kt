package com.karcz.piotr.ecom.data

data class CartModel(
    val customerEmail: String,
    val productId: Int,
    val quantity: Int
)
