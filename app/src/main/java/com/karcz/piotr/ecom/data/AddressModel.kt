package com.karcz.piotr.ecom.data

data class AddressModel(
    val street: String,
    val streetNumber: Int,
    val flatNumber: Int,
    val postalCode: String,
    val country: String,
    val city: String
)
