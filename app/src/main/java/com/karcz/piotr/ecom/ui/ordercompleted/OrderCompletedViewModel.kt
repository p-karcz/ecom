package com.karcz.piotr.ecom.ui.ordercompleted

import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderCompletedViewModel @Inject constructor() : BaseViewModel<OrderCompletedViewState, OrderCompletedNavigation, OrderCompletedInteraction>(
    OrderCompletedViewState.INITIAL
) {

    override fun onInteraction(interaction: OrderCompletedInteraction) {

    }
}
