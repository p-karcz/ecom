package com.karcz.piotr.ecom.ui.product

import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor() : BaseViewModel<ProductViewState, ProductNavigation, ProductInteraction>(
    ProductViewState.INITIAL
) {

    override fun onInteraction(interaction: ProductInteraction) {

    }
}
