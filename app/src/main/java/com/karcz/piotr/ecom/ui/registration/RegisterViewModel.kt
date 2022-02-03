package com.karcz.piotr.ecom.ui.registration

import androidx.lifecycle.viewModelScope
import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : BaseViewModel<RegisterViewState, RegisterNavigation, RegisterInteraction>(
    RegisterViewState.INITIAL
) {

    override fun onInteraction(interaction: RegisterInteraction) {
        when (interaction) {
            is RegisterInteraction.RegisterButtonClicked ->
                viewModelScope.launch { _event.emit(RegisterNavigation.NavigateToLogin) }
        }
    }
}
