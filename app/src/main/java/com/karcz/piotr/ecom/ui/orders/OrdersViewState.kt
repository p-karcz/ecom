package com.karcz.piotr.ecom.ui.orders

import com.karcz.piotr.ecom.data.domain.OrderDomainModel

sealed class OrdersViewState {

    data class Success(val orderDomains: List<OrderDomainModel>) : OrdersViewState()
    object Loading : OrdersViewState()
    object Error : OrdersViewState()

    companion object {
        val INITIAL = Success(emptyList())
    }
}
