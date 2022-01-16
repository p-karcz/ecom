package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.AllOrderDetailsTransferModel

data class AllOrderDetailsDomainModel(
    val orderDetailList: List<OrderDetailDomainModel>
) {

    fun toTransferModel() = AllOrderDetailsTransferModel(
        orderDetailList = orderDetailList.map { it.toTransferModel() }
    )
}
