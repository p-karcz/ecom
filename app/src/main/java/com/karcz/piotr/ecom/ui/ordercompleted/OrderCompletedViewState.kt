package com.karcz.piotr.ecom.ui.ordercompleted

import com.karcz.piotr.ecom.data.OrderDomainModel

sealed class OrderCompletedViewState {

    data class Success(val orderDomainModel: OrderDomainModel?) : OrderCompletedViewState()
    object Loading : OrderCompletedViewState()
    object Error : OrderCompletedViewState()

    companion object {
        val INITIAL = Success(orderDomainModel = null)
    }
}
