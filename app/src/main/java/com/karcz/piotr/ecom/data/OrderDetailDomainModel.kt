package com.karcz.piotr.ecom.data

import com.karcz.piotr.ecom.network.transferdata.OrderDetailTransferModel

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
