package com.karcz.piotr.ecom.ui.orders

import com.karcz.piotr.ecom.data.OrderModel

sealed class OrdersViewState {

    data class Success(val orders: List<OrderModel>) : OrdersViewState()
    object Loading : OrdersViewState()
    object Error : OrdersViewState()

    companion object {
        val INITIAL = Success(emptyList())
    }
}
