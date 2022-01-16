package com.karcz.piotr.ecom.ui.home

import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeViewState, HomeNavigation, HomeInteraction>(
    HomeViewState.INITIAL
) {

    override fun onInteraction(interaction: HomeInteraction) {
        when (interaction) {

        }
    }
}
