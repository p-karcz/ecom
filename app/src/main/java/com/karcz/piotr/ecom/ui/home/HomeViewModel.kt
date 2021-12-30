package com.karcz.piotr.ecom.ui.home

import com.karcz.piotr.ecom.base.ui.BaseViewModel

class HomeViewModel : BaseViewModel<HomeViewState, HomeNavigation, HomeInteraction>(HomeViewState.Success()) {

    override fun onInteraction(interaction: HomeInteraction) {
        when (interaction) {

        }
    }
}
