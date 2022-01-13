package com.karcz.piotr.ecom.network.transferdata

import com.karcz.piotr.ecom.data.CustomerDomainModel

data class CustomerTransferModel(
    val email: String? = null,
    val addressId: Int? = null,
    val name: String? = null,
    val surname: String? = null,
    val password: String? = null
) {

    fun toDomainModel(): CustomerDomainModel? {
        return if (listOf(email, addressId, name, surname, password).any { it == null }) {
            null
        } else {
            CustomerDomainModel(
                email = email!!,
                addressId = addressId!!,
                name = name!!,
                surname = surname!!,
                password = password!!
            )
        }
    }
}
