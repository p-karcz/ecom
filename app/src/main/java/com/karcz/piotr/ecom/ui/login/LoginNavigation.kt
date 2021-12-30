package com.karcz.piotr.ecom.ui.login

sealed class LoginNavigation {
    object NavigateToRegistration : LoginNavigation()
    object NavigateToHome : LoginNavigation()
    data class ShowToast(val text: String) : LoginNavigation()
}
