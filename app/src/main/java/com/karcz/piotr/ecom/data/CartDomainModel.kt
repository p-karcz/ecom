package com.karcz.piotr.ecom.data

import com.karcz.piotr.ecom.network.transferdata.CartTransferModel

data class CartDomainModel(
    val customerEmail: String,
    val productId: Int,
    val quantity: Int
) {

    fun toTransferModel() = CartTransferModel(
        customerEmail = customerEmail,
        productId = productId,
        quantity = quantity
    )
}
