package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.TokenTransferModel

data class TokenDomainModel(
    val token: String
) {

    fun toTransferModel() = TokenTransferModel(token)
}
