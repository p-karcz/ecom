package com.karcz.piotr.ecom.data

import com.karcz.piotr.ecom.network.transferdata.AllOrderDetailsTransferModel

data class AllOrderDetailsDomainModel(
    val orderDetailList: List<OrderDetailDomainModel>
) {

    fun toTransferModel() = AllOrderDetailsTransferModel(
        orderDetailList = orderDetailList.map { it.toTransferModel() }
    )
}
