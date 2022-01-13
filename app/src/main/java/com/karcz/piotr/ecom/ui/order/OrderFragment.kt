package com.karcz.piotr.ecom.ui.order

import com.karcz.piotr.ecom.base.ui.BaseStateFragment
import com.karcz.piotr.ecom.databinding.FragmentOrderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : BaseStateFragment<FragmentOrderBinding, OrderViewState, OrderNavigation, OrderInteraction>(
    FragmentOrderBinding::inflate
) {

    override fun handleViewState(viewState: OrderViewState) {
        TODO("Not yet implemented")
    }

    override fun handleNavigation(navigation: OrderNavigation) {
        TODO("Not yet implemented")
    }
}
