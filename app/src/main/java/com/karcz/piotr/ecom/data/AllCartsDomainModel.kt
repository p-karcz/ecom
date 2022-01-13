package com.karcz.piotr.ecom.data

import com.karcz.piotr.ecom.network.transferdata.AllCartsTransferModel

data class AllCartsDomainModel(
    val cartDomainList: List<CartDomainModel>,
    val productDomainList: List<ProductDomainModel>
) {

    fun toTransferModel() = AllCartsTransferModel(
        cartTransferList = cartDomainList.map { it.toTransferModel() },
        productTransferList = productDomainList.map { it.toTransferModel() }
    )
}
