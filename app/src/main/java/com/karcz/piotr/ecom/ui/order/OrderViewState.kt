package com.karcz.piotr.ecom.ui.order

import com.karcz.piotr.ecom.data.OrderDomainModel

sealed class OrderViewState {

    data class Success(val orderDomainModel: OrderDomainModel?) : OrderViewState()
    object Loading : OrderViewState()
    object Error : OrderViewState()

    companion object {
        val INITIAL = Success(orderDomainModel = null)
    }
}
