package com.karcz.piotr.ecom.data

import com.karcz.piotr.ecom.network.transferdata.CustomerTransferModel

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
