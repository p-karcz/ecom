package com.karcz.piotr.ecom.ui.product

import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import com.karcz.piotr.ecom.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : BaseStateFragment<FragmentProductBinding, ProductViewState, ProductNavigation, ProductInteraction>(
    FragmentProductBinding::inflate
) {

    override fun handleViewState(viewState: ProductViewState) {
        TODO("Not yet implemented")
    }

    override fun handleNavigation(navigation: ProductNavigation) {
        TODO("Not yet implemented")
    }
}
