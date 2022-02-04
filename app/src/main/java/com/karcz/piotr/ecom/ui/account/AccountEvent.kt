package com.karcz.piotr.ecom.ui.account

sealed class AccountEvent {
    object NavigateToLogin : AccountEvent()
}
