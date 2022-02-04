package com.karcz.piotr.ecom.ui.registration

sealed class RegisterEvent {
    object Error : RegisterEvent()
    object NavigateToLogin : RegisterEvent()
}
