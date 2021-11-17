package com.karcz.piotr.ecom.ui.login

import android.text.Editable

sealed class LoginInteraction {
    object LoginButtonClicked : LoginInteraction()
    object RegisterButtonClicked : LoginInteraction()
    data class EmailFieldChanged(val text: Editable?) : LoginInteraction()
    data class PasswordFieldChanged(val text: Editable?) : LoginInteraction()
}
