package com.karcz.piotr.ecom.ui.registration

sealed class RegisterNavigation {
    object NavigateToLogin : RegisterNavigation()
}