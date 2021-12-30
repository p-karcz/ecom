package com.karcz.piotr.ecom.ui.account

import com.karcz.piotr.ecom.base.ui.BaseViewModel

class AccountViewModel : BaseViewModel<AccountViewState, AccountNavigation, AccountInteraction>(
    AccountViewState.Success()
) {

    override fun onInteraction(interaction: AccountInteraction) {

    }
}
