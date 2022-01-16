package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.AllOrderDetailsWithProductsTransferModel

data class AllOrderDetailsWithProductsDomainModel(
    val orderDetailDomainList: List<OrderDetailDomainModel>,
    val productDomainList: List<ProductDomainModel>
) {

    fun toTransferModel() = AllOrderDetailsWithProductsTransferModel(
        orderDetailTransferList = orderDetailDomainList.map { it.toTransferModel() },
        productTransferList = productDomainList.map { it.toTransferModel() }
    )
}
