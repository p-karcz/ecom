package com.karcz.piotr.ecom.ui.account

sealed class AccountInteraction {
    object SignInButtonClicked : AccountInteraction()
    object TryAgainButtonClicked : AccountInteraction()
    object ScreenEntered : AccountInteraction()
}
