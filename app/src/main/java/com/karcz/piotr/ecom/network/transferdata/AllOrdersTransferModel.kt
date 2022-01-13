package com.karcz.piotr.ecom.network.transferdata

import com.karcz.piotr.ecom.data.AllOrdersDomainModel

data class AllOrdersTransferModel(
    val orders: List<OrderTransferModel>? = null
) {

    fun toDomain(): AllOrdersDomainModel? {
        return if (orders == null) {
            null
        } else {
            AllOrdersDomainModel(orders = orders.mapNotNull { it.toDomainModel() })
        }
    }
}
