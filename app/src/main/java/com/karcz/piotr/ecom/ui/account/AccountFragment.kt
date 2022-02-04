package com.karcz.piotr.ecom.ui.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import com.karcz.piotr.ecom.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment :
    BaseStateFragment<FragmentAccountBinding, AccountViewState, AccountEvent, AccountInteraction>(
        FragmentAccountBinding::inflate
    ) {

    private val viewModel: AccountViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.onInteraction(AccountInteraction.ScreenEntered)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeEvent(viewModel)
        observeViewState(viewModel)
        setUpListeners()
    }

    override fun handleViewState(viewState: AccountViewState) {
        with(binding) {
            when (viewState) {
                is AccountViewState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    authenticatedContainer.visibility = View.GONE
                    errorContainer.visibility = View.GONE
                    unauthenticatedContainer.visibility = View.GONE
                }
                is AccountViewState.NetworkError -> {
                    progressBar.visibility = View.GONE
                    authenticatedContainer.visibility = View.GONE
                    errorContainer.visibility = View.VISIBLE
                    unauthenticatedContainer.visibility = View.GONE
                }
                is AccountViewState.Success -> {
                    progressBar.visibility = View.GONE
                    authenticatedContainer.visibility = View.VISIBLE
                    errorContainer.visibility = View.GONE
                    unauthenticatedContainer.visibility = View.GONE

                    emailTextView.text = viewState.customerDomainModel.email
                    nameTextView.text = viewState.customerDomainModel.name
                    surnameTextView.text = viewState.customerDomainModel.surname
                }
                is AccountViewState.Unauthenticated -> {
                    progressBar.visibility = View.GONE
                    authenticatedContainer.visibility = View.GONE
                    errorContainer.visibility = View.GONE
                    unauthenticatedContainer.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun handleEvent(event: AccountEvent) {
        when (event) {
            AccountEvent.NavigateToLogin -> {
                findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
            }
        }
    }

    private fun setUpListeners() {
        binding.signIngButton.setOnClickListener {
            viewModel.onInteraction(AccountInteraction.SignInButtonClicked)
        }

        binding.tryAgainButton.setOnClickListener {
            viewModel.onInteraction(AccountInteraction.TryAgainButtonClicked)
        }
    }
}
