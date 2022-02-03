package com.karcz.piotr.ecom.ui.account

import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import com.karcz.piotr.ecom.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : BaseStateFragment<FragmentAccountBinding, AccountViewState, AccountNavigation, AccountInteraction>(
    FragmentAccountBinding::inflate
) {

    override fun handleViewState(viewState: AccountViewState) {
        TODO("Not yet implemented")
    }

    override fun handleEvent(event: AccountNavigation) {
        TODO("Not yet implemented")
    }
}
