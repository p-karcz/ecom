package com.karcz.piotr.ecom.ui.registration

sealed class RegisterViewState {

    data class Success(val isRegisterButtonEnabled: Boolean) : RegisterViewState()
    object Loading : RegisterViewState()
    object Error : RegisterViewState()

    companion object {
        val INITIAL = Success(isRegisterButtonEnabled = false)
    }
}
