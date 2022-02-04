package com.karcz.piotr.ecom.ui.registration

sealed class RegisterViewState {

    object Success : RegisterViewState()
    object Loading : RegisterViewState()

    companion object {
        val INITIAL = Success
    }
}
