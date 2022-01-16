package com.karcz.piotr.ecom.data.transfer

import com.karcz.piotr.ecom.data.domain.ProductsFilterDomainModel

data class ProductsFilterTransferModel(
    val categories: List<String>? = null,
    val priceFrom: Double? = null,
    val priceTo: Double? = null,
    val producers: List<String>? = null,
    val sizes: List<String>? = null,
    val colors: List<String>? = null
) {

    fun toDomain(): ProductsFilterDomainModel? {
        return if (listOf(
                categories,
                priceFrom,
                priceTo,
                producers,
                sizes,
                colors
            ).any { it == null }) {
            null
        } else {
            ProductsFilterDomainModel(
                categories = categories!!,
                priceFrom = priceFrom!!,
                priceTo = priceTo!!,
                producers = producers!!,
                sizes = sizes!!,
                colors = colors!!
            )
        }
    }
}
