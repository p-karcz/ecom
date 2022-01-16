package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.CustomerTransferModel

data class CustomerDomainModel(
    val email: String,
    val addressId: Int,
    val name: String,
    val surname: String,
    val password: String
) {

    fun toTransferModel() = CustomerTransferModel(
        email = email,
        addressId = addressId,
        name = name,
        surname = surname,
        password = password
    )
}
