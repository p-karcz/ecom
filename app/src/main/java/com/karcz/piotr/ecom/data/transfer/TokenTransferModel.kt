package com.karcz.piotr.ecom.data.transfer

import com.karcz.piotr.ecom.data.domain.TokenDomainModel

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
