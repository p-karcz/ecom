package com.karcz.piotr.ecom.network.transferdata

import com.karcz.piotr.ecom.data.AllOrderDetailsDomainModel

data class AllOrderDetailsTransferModel(
    val orderDetailList: List<OrderDetailTransferModel>? = null
) {

    fun toDomainModel(): AllOrderDetailsDomainModel? {
        return if (orderDetailList == null) {
            null
        } else {
            AllOrderDetailsDomainModel(
                orderDetailList = orderDetailList.mapNotNull { it.toDomainModel() }
            )
        }
    }
}
