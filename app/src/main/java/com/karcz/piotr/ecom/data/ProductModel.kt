package com.karcz.piotr.ecom.data

data class ProductModel(
    val id: Int,
    val categoryName: String,
    val name: String,
    val price: Double,
    val image: String,
    val description: String
)
