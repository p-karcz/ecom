package com.karcz.piotr.ecom.ui.ordercompleted

import com.karcz.piotr.ecom.data.OrderModel

sealed class OrderCompletedViewState {
    object Loading : OrderCompletedViewState()
    object Error : OrderCompletedViewState()
    data class Success(
        val orderModel: OrderModel? = null
    ) : OrderCompletedViewState()
}
