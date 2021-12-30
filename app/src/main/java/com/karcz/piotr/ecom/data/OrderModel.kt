package com.karcz.piotr.ecom.data

data class OrderModel(
    val id: Int,
    val customerEmail: String,
    val addressId: Int,
    val totalQuantity: Int,
    val totalPrice: Double,
    val date: String
)
