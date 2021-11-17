package com.karcz.piotr.ecom.ui.registration

data class RegisterViewState(
    val isLoading: Boolean,
    val isRegisterButtonEnabled: Boolean
){
    companion object {
        val INITIAL = RegisterViewState(
            isLoading = false,
            isRegisterButtonEnabled = false
        )
    }
}
