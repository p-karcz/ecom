package com.karcz.piotr.ecom.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.karcz.piotr.ecom.data.domain.ProductDomainModel

@Entity(tableName = "productsTable")
data class ProductEntityModel(
    @PrimaryKey val id: Int,
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

    fun toDomainModel() = ProductDomainModel(
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
