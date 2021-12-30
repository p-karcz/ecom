package com.karcz.piotr.ecom.ui.registration

import androidx.lifecycle.viewModelScope
import com.karcz.piotr.ecom.base.ui.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel<RegisterViewState, RegisterNavigation, RegisterInteraction>(
    RegisterViewState.INITIAL
) {

    override fun onInteraction(interaction: RegisterInteraction) {
        when (interaction) {
            is RegisterInteraction.RegisterButtonClicked ->
                viewModelScope.launch { _navigation.emit(RegisterNavigation.NavigateToLogin) }
        }
    }
}
