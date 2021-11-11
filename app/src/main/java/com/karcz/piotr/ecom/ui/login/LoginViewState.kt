package com.karcz.piotr.ecom.ui.login

data class LoginViewState(
    val isLoading: Boolean,
    val emailErrorMessage: String?,
    val passwordErrorMessage: String?,
    val isLoginButtonEnabled: Boolean
){

    companion object {
        val INITIAL = LoginViewState(
            isLoading = false,
            emailErrorMessage = null,
            passwordErrorMessage = null,
            isLoginButtonEnabled = false
        )
    }
}