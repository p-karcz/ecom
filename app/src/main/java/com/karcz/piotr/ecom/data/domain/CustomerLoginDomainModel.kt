package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.CustomerLoginTransferModel

data class CustomerLoginDomainModel(
    val email: String,
    val password: String
) {

    fun toTransferModel() = CustomerLoginTransferModel(
        email = email,
        password = password
    )
}
