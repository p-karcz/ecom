package com.karcz.piotr.ecom.ui.login

data class LoginViewState(
    val isLoading: Boolean,
    val isLoginButtonEnabled: Boolean
){

    companion object {
        val INITIAL = LoginViewState(
            isLoading = false,
            isLoginButtonEnabled = false
        )
    }
}