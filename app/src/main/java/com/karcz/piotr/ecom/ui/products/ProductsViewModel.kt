package com.karcz.piotr.ecom.ui.products

import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor() : BaseViewModel<ProductsViewState, ProductsNavigation, ProductsInteraction>(
    ProductsViewState.INITIAL
) {

    override fun onInteraction(interaction: ProductsInteraction) {

    }
}
