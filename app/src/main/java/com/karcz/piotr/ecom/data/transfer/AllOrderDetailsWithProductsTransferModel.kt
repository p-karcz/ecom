package com.karcz.piotr.ecom.data.transfer

import com.karcz.piotr.ecom.data.domain.AllOrderDetailsWithProductsDomainModel

data class AllOrderDetailsWithProductsTransferModel(
    val orderDetailTransferList: List<OrderDetailTransferModel>? = null,
    val productTransferList: List<ProductTransferModel>? = null
) {

    fun toDomainModel(): AllOrderDetailsWithProductsDomainModel? {
        return if (listOf(
                orderDetailTransferList,
                productTransferList
            ).any { it == null }) {
            null
        } else {
            AllOrderDetailsWithProductsDomainModel(
                orderDetailDomainList = orderDetailTransferList!!.mapNotNull { it.toDomainModel() },
                productDomainList = productTransferList!!.mapNotNull { it.toDomainModel() }
            )
        }
    }
}
