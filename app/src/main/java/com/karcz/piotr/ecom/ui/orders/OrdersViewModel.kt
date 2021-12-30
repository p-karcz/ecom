package com.karcz.piotr.ecom.ui.orders

import com.karcz.piotr.ecom.base.ui.BaseViewModel

class OrdersViewModel : BaseViewModel<OrdersViewState, OrdersNavigation, OrdersInteraction>(
    OrdersViewState.Success()
) {

    override fun onInteraction(interaction: OrdersInteraction) {

    }
}
