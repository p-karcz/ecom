package com.karcz.piotr.ecom.data

import com.karcz.piotr.ecom.network.transferdata.AllProductsTransferModel

data class AllProductsDomainModel(
    val products: List<ProductDomainModel>
) {

    fun toTransferModel() = AllProductsTransferModel(
        products = products.map { it.toTransferModel() }
    )
}
