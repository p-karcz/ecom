package com.karcz.piotr.ecom.ui.orders

import com.karcz.piotr.ecom.base.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor() : BaseViewModel<OrdersViewState, OrdersNavigation, OrdersInteraction>(
    OrdersViewState.INITIAL
) {

    override fun onInteraction(interaction: OrdersInteraction) {

    }
}
