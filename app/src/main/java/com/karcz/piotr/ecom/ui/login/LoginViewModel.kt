package com.karcz.piotr.ecom.ui.login

import android.text.Editable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginViewState: MutableStateFlow<LoginViewState> = MutableStateFlow(LoginViewState.INITIAL)
    val loginViewState: StateFlow<LoginViewState> = _loginViewState.asStateFlow()

    private val _loginActionState: MutableSharedFlow<LoginActionState> = MutableSharedFlow()
    val loginActionState: SharedFlow<LoginActionState> = _loginActionState.asSharedFlow()

    private var isEmailValid = false
    private var isPasswordValid = false

    fun onInteraction(interaction: LoginInteraction) {
        when (interaction) {
            is LoginInteraction.LoginButtonClicked ->
                viewModelScope.launch { _loginActionState.emit(LoginActionState.NavigateToHome) }
            is LoginInteraction.RegisterButtonClicked ->
                viewModelScope.launch { _loginActionState.emit(LoginActionState.NavigateToRegistration) }
            is LoginInteraction.EmailFieldChanged -> validateEmailInput(interaction.text)
            is LoginInteraction.PasswordFieldChanged -> validatePasswordInput(interaction.text)
        }
    }

    private fun validateEmailInput(text: Editable?) {
        if (text == null || !text.contains('@')) {
            isEmailValid = false
            _loginViewState.value = _loginViewState.value.copy(isLoginButtonEnabled = isEmailValid)
        } else if (isPasswordValid) {
            isEmailValid = true
            _loginViewState.value = _loginViewState.value.copy(isLoginButtonEnabled = isEmailValid)
        } else {
            isEmailValid = true
        }
    }

    private fun validatePasswordInput(text: Editable?) {
        if (text == null || text.length < 8) {
            isPasswordValid = false
            _loginViewState.value = _loginViewState.value.copy(isLoginButtonEnabled = isPasswordValid)
        } else if (isEmailValid) {
            isPasswordValid = true
            _loginViewState.value = _loginViewState.value.copy(isLoginButtonEnabled = isPasswordValid)
        } else {
            isPasswordValid = true
        }
    }
}