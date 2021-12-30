package com.karcz.piotr.ecom.ui.cart

import com.karcz.piotr.ecom.base.ui.BaseViewModel

class CartViewModel : BaseViewModel<CartViewState, CartNavigation, CartInteraction>(
    CartViewState.Success()
) {

    override fun onInteraction(interaction: CartInteraction) {

    }
}
