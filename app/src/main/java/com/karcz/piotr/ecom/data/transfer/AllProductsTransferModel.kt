package com.karcz.piotr.ecom.data.transfer

import com.karcz.piotr.ecom.data.domain.AllProductsDomainModel

data class AllProductsTransferModel(
    val products: List<ProductTransferModel>? = null
) {

    fun toDomainModel(): AllProductsDomainModel? {
        return if (products == null) {
            null
        } else {
            AllProductsDomainModel(
                products = products.mapNotNull { it.toDomainModel() }
            )
        }
    }
}
