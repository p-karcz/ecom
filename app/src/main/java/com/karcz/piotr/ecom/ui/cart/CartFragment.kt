package com.karcz.piotr.ecom.ui.cart

import com.karcz.piotr.ecom.base.ui.BaseStateFragment
import com.karcz.piotr.ecom.databinding.FragmentCartBinding

class CartFragment : BaseStateFragment<FragmentCartBinding, CartViewState, CartNavigation, CartInteraction>(
    FragmentCartBinding::inflate
) {

    override fun handleViewState(viewState: CartViewState) {
        TODO("Not yet implemented")
    }

    override fun handleNavigation(navigation: CartNavigation) {
        TODO("Not yet implemented")
    }
}
