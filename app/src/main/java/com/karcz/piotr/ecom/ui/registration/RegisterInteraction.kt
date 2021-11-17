package com.karcz.piotr.ecom.ui.registration

sealed class RegisterInteraction {
    object RegisterButtonClicked : RegisterInteraction()
}