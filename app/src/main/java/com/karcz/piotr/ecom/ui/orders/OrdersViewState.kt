package com.karcz.piotr.ecom.ui.orders

import com.karcz.piotr.ecom.data.OrderModel

sealed class OrdersViewState {
    object Loading : OrdersViewState()
    object Error : OrdersViewState()
    data class Success(
        val orders: List<OrderModel> = emptyList()
    ) : OrdersViewState()
}
