package com.karcz.piotr.ecom.ui.login

sealed class LoginViewState {

    data class Success(val isLoginButtonEnabled: Boolean) : LoginViewState()
    object Loading : LoginViewState()
    object Error : LoginViewState()

    companion object {
        val INITIAL = Success(isLoginButtonEnabled = false)
    }
}
