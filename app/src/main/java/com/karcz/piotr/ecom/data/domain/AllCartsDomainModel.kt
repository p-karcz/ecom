package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.AllCartsTransferModel

data class AllCartsDomainModel(
    val cartDomainList: List<CartDomainModel>,
    val productDomainList: List<ProductDomainModel>
) {

    fun toTransferModel() = AllCartsTransferModel(
        cartTransferList = cartDomainList.map { it.toTransferModel() },
        productTransferList = productDomainList.map { it.toTransferModel() }
    )
}
