package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.entity.ProductEntityModel
import com.karcz.piotr.ecom.data.transfer.ProductTransferModel

data class ProductDomainModel(
    val id: Int,
    val name: String,
    val price: Double,
    val image: String,
    val description: String,
    val category: String,
    val producer: String,
    val size: String,
    val color: String,
    val popularity: Int,
    val quantity: Int,
    val productCode: Int
) {

    fun toTransferModel() = ProductTransferModel(
        id = id,
        name = name,
        price = price,
        image = image,
        description = description,
        category = category,
        producer = producer,
        size = size,
        color = color,
        popularity = popularity,
        quantity = quantity,
        productCode = productCode
    )

    fun toEntityModel() = ProductEntityModel(
        id = id,
        name = name,
        price = price,
        image = image,
        description = description,
        category = category,
        producer = producer,
        size = size,
        color = color,
        popularity = popularity,
        quantity = quantity,
        productCode = productCode
    )
}
