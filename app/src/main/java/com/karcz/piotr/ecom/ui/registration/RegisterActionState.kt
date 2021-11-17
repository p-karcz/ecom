package com.karcz.piotr.ecom.ui.registration

sealed class RegisterActionState {
    object NavigateToLogin : RegisterActionState()
}