package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.CustomerRegistrationTransferModel

data class CustomerRegistrationDomainModel(
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
) {

    fun toTransferModel() = CustomerRegistrationTransferModel(
        email = email,
        password = password,
        name = name,
        surname = surname,
        street = street,
        streetNumber = streetNumber,
        flatNumber = flatNumber,
        postalCode = postalCode,
        country = country,
        city = city
    )
}
