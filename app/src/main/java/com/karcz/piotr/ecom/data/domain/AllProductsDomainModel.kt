package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.AllProductsTransferModel

data class AllProductsDomainModel(
    val products: List<ProductDomainModel>
) {

    fun toTransferModel() = AllProductsTransferModel(
        products = products.map { it.toTransferModel() }
    )
}
