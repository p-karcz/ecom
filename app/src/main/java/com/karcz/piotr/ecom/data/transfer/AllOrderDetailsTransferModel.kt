package com.karcz.piotr.ecom.data.transfer

import com.karcz.piotr.ecom.data.domain.AllOrderDetailsDomainModel

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
