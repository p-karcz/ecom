package com.karcz.piotr.ecom.ui.login

sealed class LoginInteraction {

    object LoginButtonClick: LoginInteraction()
    object RegisterButtonClick: LoginInteraction()
}
