package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.CartTransferModel

data class CartDomainModel(
    val productId: Int,
    val quantity: Int
) {

    fun toTransferModel() = CartTransferModel(
        productId = productId,
        quantity = quantity
    )
}
