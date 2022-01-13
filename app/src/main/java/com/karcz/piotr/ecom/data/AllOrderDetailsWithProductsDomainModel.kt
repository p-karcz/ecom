package com.karcz.piotr.ecom.data

import com.karcz.piotr.ecom.network.transferdata.AllOrderDetailsWithProductsTransferModel

data class AllOrderDetailsWithProductsDomainModel(
    val orderDetailDomainList: List<OrderDetailDomainModel>,
    val productDomainList: List<ProductDomainModel>
) {

    fun toTransferModel() = AllOrderDetailsWithProductsTransferModel(
        orderDetailTransferList = orderDetailDomainList.map { it.toTransferModel() },
        productTransferList = productDomainList.map { it.toTransferModel() }
    )
}
