package com.karcz.piotr.ecom.data.transfer

import com.karcz.piotr.ecom.data.domain.SingleListDomainModel

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
