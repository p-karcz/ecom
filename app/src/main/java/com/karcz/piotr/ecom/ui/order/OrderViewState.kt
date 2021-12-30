package com.karcz.piotr.ecom.ui.order

import com.karcz.piotr.ecom.data.OrderModel

sealed class OrderViewState {
    object Loading : OrderViewState()
    object Error : OrderViewState()
    data class Success(
        val orderModel: OrderModel? = null
    ) : OrderViewState()
}
