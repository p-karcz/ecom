package com.karcz.piotr.ecom.ui.cart

import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : BaseViewModel<CartViewState, CartNavigation, CartInteraction>(
    CartViewState.INITIAL
) {

    override fun onInteraction(interaction: CartInteraction) {

    }
}
