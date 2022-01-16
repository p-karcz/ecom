package com.karcz.piotr.ecom.data.domain

import com.karcz.piotr.ecom.data.transfer.SingleListTransferModel

data class SingleListDomainModel<T>(
    val values: List<T>
) {
    fun toTransferModel() = SingleListTransferModel(values)
}
