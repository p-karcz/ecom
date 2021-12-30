package com.karcz.piotr.ecom.data

data class OrderDetailModel(
    val orderId: Int,
    val productId: Int,
    val quantity: Int,
    val price: Double
)
