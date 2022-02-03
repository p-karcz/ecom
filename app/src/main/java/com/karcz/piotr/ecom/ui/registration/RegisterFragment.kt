package com.karcz.piotr.ecom.ui.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import com.karcz.piotr.ecom.ui.base.visibleOrGone
import com.karcz.piotr.ecom.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment
    : BaseStateFragment<FragmentRegistrationBinding, RegisterViewState, RegisterNavigation, RegisterInteraction>(
    FragmentRegistrationBinding::inflate
) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListener()
        observeViewState(viewModel)
        observeEvent(viewModel)
    }

    override fun handleViewState(viewState: RegisterViewState) {
        when (viewState) {
            is RegisterViewState.Success -> {
                binding.loadingProgressBar.visibleOrGone(viewState.isRegisterButtonEnabled)
                binding.registerButton.isEnabled = viewState.isRegisterButtonEnabled
            }
            is RegisterViewState.Loading -> { }
            is RegisterViewState.Error -> { }
        }
    }

    override fun handleEvent(event: RegisterNavigation) = when (event) {
        is RegisterNavigation.NavigateToLogin ->
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
    }

    private fun setUpOnClickListener() {
        binding.registerButton.setOnClickListener {
            viewModel.onInteraction(RegisterInteraction.RegisterButtonClicked)
        }
    }
}
