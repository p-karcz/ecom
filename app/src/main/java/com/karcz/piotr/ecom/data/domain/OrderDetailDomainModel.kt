package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.OrderDetailTransferModel

data class OrderDetailDomainModel(
    val orderId: Int,
    val productId: Int,
    val quantity: Int,
    val price: Double
) {

    fun toTransferModel() = OrderDetailTransferModel(
        orderId = orderId,
        productId = productId,
        quantity = quantity,
        price = price
    )
}
