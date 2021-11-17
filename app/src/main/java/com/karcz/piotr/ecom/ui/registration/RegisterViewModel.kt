package com.karcz.piotr.ecom.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _registerViewState: MutableStateFlow<RegisterViewState> =
        MutableStateFlow(RegisterViewState.INITIAL)
    val registerViewState: StateFlow<RegisterViewState> = _registerViewState.asStateFlow()

    private val _registerActionState: MutableSharedFlow<RegisterActionState> = MutableSharedFlow()
    val registerActionState: SharedFlow<RegisterActionState> = _registerActionState.asSharedFlow()

    fun onInteraction(interaction: RegisterInteraction) {
        when (interaction) {
            is RegisterInteraction.RegisterButtonClicked ->
                viewModelScope.launch { _registerActionState.emit(RegisterActionState.NavigateToLogin) }
        }
    }
}