package com.karcz.piotr.ecom.data.transfer

data class CustomerRegistrationTransferModel(
    val email: String,
    val password: String,
    val name: String,
    val surname: String,
    val street: String,
    val streetNumber: Int,
    val flatNumber: Int,
    val postalCode: String,
    val country: String,
    val city: String
)
