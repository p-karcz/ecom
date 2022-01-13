package com.karcz.piotr.ecom.network.transferdata

import com.karcz.piotr.ecom.data.TokenDomainModel

data class TokenTransferModel(
    val token: String? = null
) {

    fun toDomainModel(): TokenDomainModel? {
        return if (token == null) {
            null
        } else {
            TokenDomainModel(token)
        }
    }
}
