package com.karcz.piotr.ecom.data

import com.karcz.piotr.ecom.network.transferdata.AddressTransferModel

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
