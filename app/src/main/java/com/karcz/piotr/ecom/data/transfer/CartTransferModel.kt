package com.karcz.piotr.ecom.data.transfer

import com.karcz.piotr.ecom.data.domain.CartDomainModel

data class CartTransferModel(
    val productId: Int? = null,
    val quantity: Int? = null
) {

    fun toDomainModel(): CartDomainModel? {
        return if (listOf(
                productId,
                quantity
            ).any { it == null }) {
            null
        } else {
            CartDomainModel(
                productId = productId!!,
                quantity = quantity!!
            )
        }
    }
}
