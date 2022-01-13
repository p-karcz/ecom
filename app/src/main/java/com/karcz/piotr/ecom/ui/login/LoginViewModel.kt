package com.karcz.piotr.ecom.ui.login

import android.text.Editable
import androidx.lifecycle.viewModelScope
import com.karcz.piotr.ecom.base.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel<LoginViewState, LoginNavigation, LoginInteraction>(
    LoginViewState.INITIAL
) {

    private var isEmailValid = false
    private var isPasswordValid = false

    override fun onInteraction(interaction: LoginInteraction) {
        when (interaction) {
            is LoginInteraction.LoginButtonClicked ->
                viewModelScope.launch { _navigation.emit(LoginNavigation.NavigateToHome) }
            is LoginInteraction.RegisterButtonClicked ->
                viewModelScope.launch { _navigation.emit(LoginNavigation.NavigateToRegistration) }
            is LoginInteraction.EmailFieldChanged -> validateEmailInput(interaction.text)
            is LoginInteraction.PasswordFieldChanged -> validatePasswordInput(interaction.text)
        }
    }

    private fun validateEmailInput(text: Editable?) {
        if (text == null || !text.contains('@')) {
            isEmailValid = false
            _viewState.value = LoginViewState.Success(isLoginButtonEnabled = isEmailValid)
        } else if (isPasswordValid) {
            isEmailValid = true
            _viewState.value = LoginViewState.Success(isLoginButtonEnabled = isEmailValid)
        } else {
            isEmailValid = true
        }
    }

    private fun validatePasswordInput(text: Editable?) {
        if (text == null || text.length < 8) {
            isPasswordValid = false
            _viewState.value = LoginViewState.Success(isLoginButtonEnabled = isPasswordValid)
        } else if (isEmailValid) {
            isPasswordValid = true
            _viewState.value = LoginViewState.Success(isLoginButtonEnabled = isPasswordValid)
        } else {
            isPasswordValid = true
        }
    }
}
