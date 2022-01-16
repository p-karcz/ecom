package com.karcz.piotr.ecom.ui.ordercompleted

import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import com.karcz.piotr.ecom.databinding.FragmentOrderCompletedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderCompletedFragment : BaseStateFragment<FragmentOrderCompletedBinding, OrderCompletedViewState, OrderCompletedNavigation, OrderCompletedInteraction>(
    FragmentOrderCompletedBinding::inflate
) {

    override fun handleViewState(viewState: OrderCompletedViewState) {
        TODO("Not yet implemented")
    }

    override fun handleNavigation(navigation: OrderCompletedNavigation) {
        TODO("Not yet implemented")
    }
}
