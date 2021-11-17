package com.karcz.piotr.ecom.ui.login

sealed class LoginActionState {
    object NavigateToRegistration : LoginActionState()
    object NavigateToHome : LoginActionState()
    data class ShowToast(val text: String) : LoginActionState()
}
