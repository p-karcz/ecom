package com.karcz.piotr.ecom.ui.cart

import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import com.karcz.piotr.ecom.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseStateFragment<FragmentCartBinding, CartViewState, CartNavigation, CartInteraction>(
    FragmentCartBinding::inflate
) {

    override fun handleViewState(viewState: CartViewState) {
        TODO("Not yet implemented")
    }

    override fun handleEvent(event: CartNavigation) {
        TODO("Not yet implemented")
    }
}
