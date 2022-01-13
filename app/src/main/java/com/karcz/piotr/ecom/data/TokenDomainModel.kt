package com.karcz.piotr.ecom.data

import com.karcz.piotr.ecom.network.transferdata.TokenTransferModel

data class TokenDomainModel(
    val token: String
) {

    fun toTransferModel() = TokenTransferModel(token)
}
