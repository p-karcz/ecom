package com.karcz.piotr.ecom.data

import com.karcz.piotr.ecom.network.transferdata.AllOrdersTransferModel

data class AllOrdersDomainModel(
    val orders: List<OrderDomainModel>
) {

    fun toTransferModel() = AllOrdersTransferModel(
        orders = orders.map { it.toTransferModel() }
    )
}
