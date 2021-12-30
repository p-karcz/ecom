package com.karcz.piotr.ecom.ui.ordercompleted

import com.karcz.piotr.ecom.base.ui.BaseViewModel

class OrderCompletedViewModel : BaseViewModel<OrderCompletedViewState, OrderCompletedNavigation, OrderCompletedInteraction>(
    OrderCompletedViewState.Success()
) {

    override fun onInteraction(interaction: OrderCompletedInteraction) {

    }
}
