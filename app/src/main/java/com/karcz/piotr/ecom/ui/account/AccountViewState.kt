package com.karcz.piotr.ecom.ui.account

import com.karcz.piotr.ecom.data.domain.CustomerDomainModel

sealed class AccountViewState {

    data class Success(val customerDomainModel: CustomerDomainModel) : AccountViewState()
    object Loading : AccountViewState()
    object NetworkError : AccountViewState()
    object Unauthenticated : AccountViewState()

    companion object {
        val INITIAL = Loading
    }
}
