package com.karcz.piotr.ecom.data

data class CustomerModel(
    val email: String,
    val addressId: Int?,
    val name: String,
    val surname: String,
    val password: String
)
