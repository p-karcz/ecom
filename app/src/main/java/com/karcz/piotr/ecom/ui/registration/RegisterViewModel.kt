package com.karcz.piotr.ecom.ui.registration

import androidx.lifecycle.viewModelScope
import com.karcz.piotr.ecom.data.Resource
import com.karcz.piotr.ecom.data.domain.CustomerRegistrationDomainModel
import com.karcz.piotr.ecom.data.repository.AuthorizationRepository
import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
) :
    BaseViewModel<RegisterViewState, RegisterEvent, RegisterInteraction>(
        RegisterViewState.INITIAL
    ) {

    override fun onInteraction(interaction: RegisterInteraction) {
        when (interaction) {
            is RegisterInteraction.RegisterButtonClicked -> {
                if (listOf(
                        interaction.email,
                        interaction.password,
                        interaction.name,
                        interaction.surname,
                        interaction.street,
                        interaction.streetNumber,
                        interaction.flatNumber,
                        interaction.postalCode,
                        interaction.country,
                        interaction.city
                    ).any { it == null }
                ) {
                    viewModelScope.launch {
                        _event.emit(RegisterEvent.Error)
                    }
                    return
                } else {
                    register(
                        CustomerRegistrationDomainModel(
                            email = interaction.email!!,
                            password = interaction.password!!,
                            name = interaction.name!!,
                            surname = interaction.surname!!,
                            street = interaction.street!!,
                            streetNumber = interaction.streetNumber!!.toInt(),
                            flatNumber = interaction.flatNumber!!.toInt(),
                            postalCode = interaction.postalCode!!,
                            country = interaction.country!!,
                            city = interaction.city!!
                        )
                    )
                }
            }
        }
    }

    private fun register(customerRegistrationDomainModel: CustomerRegistrationDomainModel) {
        _viewState.value = RegisterViewState.Loading
        viewModelScope.launch {
            authorizationRepository.register(customerRegistrationDomainModel).collect { tokenResource ->
                when(tokenResource) {
                    is Resource.NetworkError -> {
                        _viewState.value = RegisterViewState.Success
                        _event.emit(RegisterEvent.Error)
                    }
                    is Resource.NetworkSuccess -> {
                        _viewState.value = RegisterViewState.Success
                        _event.emit(RegisterEvent.NavigateToLogin)
                    }
                    else -> {
                        throw IllegalStateException("Unexpected Resource type: ${tokenResource.javaClass}")
                    }
                }
            }
        }
    }
}
