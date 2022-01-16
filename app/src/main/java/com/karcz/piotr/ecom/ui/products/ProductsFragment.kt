package com.karcz.piotr.ecom.ui.products

import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import com.karcz.piotr.ecom.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : BaseStateFragment<FragmentProductsBinding, ProductsViewState, ProductsNavigation, ProductsInteraction>(
    FragmentProductsBinding::inflate
) {

    override fun handleViewState(viewState: ProductsViewState) {
        TODO("Not yet implemented")
    }

    override fun handleNavigation(navigation: ProductsNavigation) {
        TODO("Not yet implemented")
    }
}
