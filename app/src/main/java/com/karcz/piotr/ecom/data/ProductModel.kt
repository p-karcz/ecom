package com.karcz.piotr.ecom.data

data class ProductModel(
    val id: Int,
    val name: String,
    val price: Double,
    val image: String,
    val description: String,
    val category: String,
    val producer: String,
    val size: String,
    val color: String,
    val popularity: Int
)
