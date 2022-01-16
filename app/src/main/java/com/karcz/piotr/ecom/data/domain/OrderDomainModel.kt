package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.OrderTransferModel

data class OrderDomainModel(
    val id: Int,
    val customerEmail: String,
    val addressId: Int,
    val totalQuantity: Int,
    val totalPrice: Double,
    val date: String
) {

    fun toTransferModel() = OrderTransferModel(
        id = id,
        customerEmail = customerEmail,
        addressId = addressId,
        totalQuantity = totalQuantity,
        totalPrice = totalPrice,
        date = date
    )
}
