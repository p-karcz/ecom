package com.karcz.piotr.ecom.ui.account

import com.karcz.piotr.ecom.base.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor() : BaseViewModel<AccountViewState, AccountNavigation, AccountInteraction>(
    AccountViewState.INITIAL
) {

    override fun onInteraction(interaction: AccountInteraction) {

    }
}
