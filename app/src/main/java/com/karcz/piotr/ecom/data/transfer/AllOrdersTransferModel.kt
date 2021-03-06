package com.karcz.piotr.ecom.data.transfer

import com.karcz.piotr.ecom.data.domain.AllOrdersDomainModel

data class AllOrdersTransferModel(
    val orders: List<OrderTransferModel>? = null
) {

    fun toDomainModel(): AllOrdersDomainModel? {
        return if (orders == null) {
            null
        } else {
            AllOrdersDomainModel(orders = orders.mapNotNull { it.toDomainModel() })
        }
    }
}
