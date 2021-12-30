package com.karcz.piotr.ecom.ui.product

import com.karcz.piotr.ecom.base.ui.BaseViewModel

class ProductViewModel : BaseViewModel<ProductViewState, ProductNavigation, ProductInteraction>(
    ProductViewState.Success()
) {

    override fun onInteraction(interaction: ProductInteraction) {

    }
}
