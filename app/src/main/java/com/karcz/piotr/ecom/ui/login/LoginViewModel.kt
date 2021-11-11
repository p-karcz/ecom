package com.karcz.piotr.ecom.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginViewState: MutableStateFlow<LoginViewState> = MutableStateFlow(LoginViewState.INITIAL)
    val loginViewState: StateFlow<LoginViewState> = _loginViewState.asStateFlow()

    private val _loginActionState: MutableSharedFlow<LoginActionState> = MutableSharedFlow()
    val loginActionState: SharedFlow<LoginActionState> = _loginActionState.asSharedFlow()

    fun onInteraction(interaction: LoginInteraction) {
        when (interaction) {
            LoginInteraction.LoginButtonClick ->
                viewModelScope.launch { _loginActionState.emit(LoginActionState.NavigateToHome) }
            LoginInteraction.RegisterButtonClick ->
                viewModelScope.launch { _loginActionState.emit(LoginActionState.NavigateToRegistration) }
        }
    }
}