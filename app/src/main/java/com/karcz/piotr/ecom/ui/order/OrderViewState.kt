package com.karcz.piotr.ecom.ui.order

import com.karcz.piotr.ecom.data.OrderModel

sealed class OrderViewState {

    data class Success(val orderModel: OrderModel?) : OrderViewState()
    object Loading : OrderViewState()
    object Error : OrderViewState()

    companion object {
        val INITIAL = Success(orderModel = null)
    }
}
