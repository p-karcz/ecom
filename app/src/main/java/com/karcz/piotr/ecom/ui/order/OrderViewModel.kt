package com.karcz.piotr.ecom.ui.order

import com.karcz.piotr.ecom.base.ui.BaseViewModel

class OrderViewModel : BaseViewModel<OrderViewState, OrderNavigation, OrderInteraction>(
    OrderViewState.Success()
) {

    override fun onInteraction(interaction: OrderInteraction) {

    }
}
