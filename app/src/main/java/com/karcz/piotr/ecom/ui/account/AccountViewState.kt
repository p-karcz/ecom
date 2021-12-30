package com.karcz.piotr.ecom.ui.account

import com.karcz.piotr.ecom.data.CustomerModel

sealed class AccountViewState {
    object Loading : AccountViewState()
    object Error : AccountViewState()
    data class Success(
        val customerModel: CustomerModel? = null
    ) : AccountViewState()
}
