package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.AllOrdersTransferModel

data class AllOrdersDomainModel(
    val orders: List<OrderDomainModel>
) {

    fun toTransferModel() = AllOrdersTransferModel(
        orders = orders.map { it.toTransferModel() }
    )
}
