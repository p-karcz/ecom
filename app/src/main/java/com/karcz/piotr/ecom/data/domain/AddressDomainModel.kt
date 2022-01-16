package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.AddressTransferModel

data class AddressDomainModel(
    val id: Int,
    val street: String,
    val streetNumber: Int,
    val flatNumber: Int,
    val postalCode: String,
    val country: String,
    val city: String
) {

    fun toTransferModel() = AddressTransferModel(
        id = id,
        street = street,
        streetNumber = streetNumber,
        flatNumber = flatNumber,
        postalCode = postalCode,
        country = country,
        city = city
    )
}
