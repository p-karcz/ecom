package com.karcz.piotr.ecom.ui.login

sealed class LoginActionState {

    object NavigateToRegistration : LoginActionState()
    object NavigateToHome : LoginActionState()
    data class Error(val errorMessage: String, val throwable: Throwable) : LoginActionState()
}
