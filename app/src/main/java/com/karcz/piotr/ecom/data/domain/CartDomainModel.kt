package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.CartTransferModel

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
