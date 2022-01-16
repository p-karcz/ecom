package com.karcz.piotr.ecom.data.transfer

import com.karcz.piotr.ecom.data.domain.AllCartsDomainModel

data class AllCartsTransferModel(
    val cartTransferList: List<CartTransferModel>? = null,
    val productTransferList: List<ProductTransferModel>? = null
) {

    fun toDomainModel(): AllCartsDomainModel? {
        return if (listOf(
                cartTransferList,
                productTransferList
            ).any { it == null }) {
            null
        } else {
            AllCartsDomainModel(
                cartDomainList = cartTransferList!!.mapNotNull { it.toDomainModel() },
                productDomainList = productTransferList!!.mapNotNull { it.toDomainModel() }
            )
        }
    }
}
