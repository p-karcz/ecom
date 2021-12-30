package com.karcz.piotr.ecom.ui.products

import com.karcz.piotr.ecom.base.ui.BaseViewModel

class ProductsViewModel : BaseViewModel<ProductsViewState, ProductsNavigation, ProductsInteraction>(
    ProductsViewState.Success()
) {

    override fun onInteraction(interaction: ProductsInteraction) {

    }
}
