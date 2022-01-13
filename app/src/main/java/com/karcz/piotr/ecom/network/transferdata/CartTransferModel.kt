package com.karcz.piotr.ecom.network.transferdata

import com.karcz.piotr.ecom.data.CartDomainModel

data class CartTransferModel(
    val customerEmail: String? = null,
    val productId: Int? = null,
    val quantity: Int? = null
) {

    fun toDomainModel(): CartDomainModel? {
        return if (listOf(customerEmail, productId, quantity).any { it == null }) {
            null
        } else {
            CartDomainModel(
                customerEmail = customerEmail!!,
                productId = productId!!,
                quantity = quantity!!
            )
        }
    }
}
