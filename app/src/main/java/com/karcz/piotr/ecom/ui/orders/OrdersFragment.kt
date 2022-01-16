package com.karcz.piotr.ecom.ui.orders

import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import com.karcz.piotr.ecom.databinding.FragmentOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : BaseStateFragment<FragmentOrdersBinding, OrdersViewState, OrdersNavigation, OrdersInteraction>(
    FragmentOrdersBinding::inflate
) {

    override fun handleViewState(viewState: OrdersViewState) {
        TODO("Not yet implemented")
    }

    override fun handleNavigation(navigation: OrdersNavigation) {
        TODO("Not yet implemented")
    }
}
