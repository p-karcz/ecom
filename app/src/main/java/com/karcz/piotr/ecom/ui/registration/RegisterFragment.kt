package com.karcz.piotr.ecom.ui.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.base.ui.BaseStateFragment
import com.karcz.piotr.ecom.common.ui.visibleOrGone
import com.karcz.piotr.ecom.databinding.FragmentRegistrationBinding

class RegisterFragment
    : BaseStateFragment<FragmentRegistrationBinding, RegisterViewState, RegisterNavigation, RegisterInteraction>(
    FragmentRegistrationBinding::inflate
) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListener()
        observeViewState(viewModel)
        observeNavigation(viewModel)
    }

    override fun handleViewState(viewState: RegisterViewState) {
        binding.loadingProgressBar.visibleOrGone(viewState.isRegisterButtonEnabled)
        binding.registerButton.isEnabled = viewState.isRegisterButtonEnabled
    }

    override fun handleNavigation(navigation: RegisterNavigation) = when (navigation) {
        is RegisterNavigation.NavigateToLogin ->
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
    }

    private fun setUpOnClickListener() {
        binding.registerButton.setOnClickListener {
            viewModel.onInteraction(RegisterInteraction.RegisterButtonClicked)
        }
    }
}
