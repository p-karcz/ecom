package com.karcz.piotr.ecom.network.transferdata

import com.karcz.piotr.ecom.data.SingleListDomainModel

data class SingleListTransferModel<T>(
    val values: List<T>? = null
) {

    fun toDomainModel(): SingleListDomainModel<T>? {
        return if (values == null) {
            null
        } else {
            SingleListDomainModel(values)
        }
    }
}
