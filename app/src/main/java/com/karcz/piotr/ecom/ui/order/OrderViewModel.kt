package com.karcz.piotr.ecom.ui.order

import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor() : BaseViewModel<OrderViewState, OrderNavigation, OrderInteraction>(
    OrderViewState.INITIAL
) {

    override fun onInteraction(interaction: OrderInteraction) {

    }
}
