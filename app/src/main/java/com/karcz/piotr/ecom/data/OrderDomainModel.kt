package com.karcz.piotr.ecom.data

import com.karcz.piotr.ecom.network.transferdata.OrderTransferModel

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

    companion object {
        const val DEFAULT_NOT_USED_ORDER_ID = -1
    }
}
