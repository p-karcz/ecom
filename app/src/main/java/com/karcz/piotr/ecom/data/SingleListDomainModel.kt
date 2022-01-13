package com.karcz.piotr.ecom.data

import com.karcz.piotr.ecom.network.transferdata.SingleListTransferModel

data class SingleListDomainModel<T>(
    val values: List<T>
) {

    fun toTransferModel() = SingleListTransferModel(values)
}
