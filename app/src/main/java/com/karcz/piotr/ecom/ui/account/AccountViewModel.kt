package com.karcz.piotr.ecom.ui.account

import androidx.lifecycle.viewModelScope
import com.karcz.piotr.ecom.data.Resource
import com.karcz.piotr.ecom.data.repository.CustomerRepository
import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val customerRepository: CustomerRepository
) : BaseViewModel<AccountViewState, AccountEvent, AccountInteraction>(
    AccountViewState.INITIAL
) {

    init {
        loadData()
    }

    override fun onInteraction(interaction: AccountInteraction) {
        when(interaction) {
            AccountInteraction.SignInButtonClicked -> {
                viewModelScope.launch {
                    _event.emit(AccountEvent.NavigateToLogin)
                }
            }
            AccountInteraction.TryAgainButtonClicked, AccountInteraction.ScreenEntered -> {
                loadData()
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _viewState.value = AccountViewState.Loading
            customerRepository.getCustomer().collect { customerResource ->
                when(customerResource) {
                    is Resource.NetworkError -> {
                        _viewState.value = AccountViewState.NetworkError
                    }
                    is Resource.NetworkSuccess -> {
                        _viewState.value = AccountViewState.Success(
                            customerDomainModel = customerResource.data
                        )
                    }
                    is Resource.NetworkUnauthenticated -> {
                        _viewState.value = AccountViewState.Unauthenticated
                    }
                    else -> {
                        throw IllegalStateException("Unexpected Resource type: ${customerResource.javaClass}")
                    }
                }
            }
        }
    }
}
