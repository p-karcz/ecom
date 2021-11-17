package com.karcz.piotr.ecom.ui.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.base.ui.BaseBindingFragment
import com.karcz.piotr.ecom.databinding.FragmentRegistrationBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RegisterFragment :
    BaseBindingFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListener()
        observeViewState()
        observeActionState()
    }

    private fun setUpOnClickListener() {
        binding.registerButton.setOnClickListener {
            registerViewModel.onInteraction(RegisterInteraction.RegisterButtonClicked)
        }
    }

    private fun observeViewState() = viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            registerViewModel.registerViewState.collect { registerViewState ->
                handleViewState(registerViewState)
            }
        }
    }

    private fun handleViewState(registerViewState: RegisterViewState) {
        binding.registerButton.isEnabled = registerViewState.isRegisterButtonEnabled
    }

    private fun observeActionState() = viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            registerViewModel.registerActionState.collect { registerActionState ->
                handleActionState(registerActionState)
            }
        }
    }

    private fun handleActionState(registerActionState: RegisterActionState) = when (registerActionState) {
        is RegisterActionState.NavigateToLogin ->
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
    }
}