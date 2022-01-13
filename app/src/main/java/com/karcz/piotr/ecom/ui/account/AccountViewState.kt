package com.karcz.piotr.ecom.ui.account

import com.karcz.piotr.ecom.data.CustomerModel

sealed class AccountViewState {

    data class Success(val customerModel: CustomerModel?) : AccountViewState()
    object Loading : AccountViewState()
    object Error : AccountViewState()

    companion object {
        val INITIAL = Success(customerModel = null)
    }
}
