package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.ProductsFilterTransferModel

data class ProductsFilterDomainModel(
    val categories: List<String>,
    val priceFrom: Double,
    val priceTo: Double,
    val producers: List<String>,
    val sizes: List<String>,
    val colors: List<String>
) {

    fun toTransferModel() = ProductsFilterTransferModel(
        categories = categories,
        priceFrom = priceFrom,
        priceTo = priceTo,
        producers = producers,
        sizes = sizes,
        colors = colors
    )
}
