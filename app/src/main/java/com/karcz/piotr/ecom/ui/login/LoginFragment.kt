package com.karcz.piotr.ecom.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.karcz.piotr.ecom.base.BaseBindingFragment
import com.karcz.piotr.ecom.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginFragment : BaseBindingFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
        observeViewState()
        observeActionState()
    }

    private fun setUpOnClickListeners() {
        binding.registerButton.setOnClickListener {
            loginViewModel.onInteraction(LoginInteraction.RegisterButtonClick)
        }

        binding.loginButton.setOnClickListener {
            loginViewModel.onInteraction(LoginInteraction.LoginButtonClick)
        }
    }

    private fun observeViewState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginViewState.collect { loginViewState ->
                    handleViewState(loginViewState)
                }
            }
        }
    }

    private fun handleViewState(loginViewState: LoginViewState) {
        binding.loadingProgressBar.isVisible = loginViewState.isLoading
        binding.emailEditText.error = loginViewState.emailErrorMessage
        binding.passwordEditText.error = loginViewState.passwordErrorMessage
        binding.loginButton.isEnabled = loginViewState.isLoginButtonEnabled
    }

    private fun observeActionState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginActionState.collect { loginActionState ->
                    handleActionState(loginActionState)
                }
            }
        }
    }

    private fun handleActionState(loginActionState: LoginActionState) {
        when (loginActionState) {
            is LoginActionState.NavigateToHome -> findNavController().navigate()
            is LoginActionState.NavigateToRegistration -> findNavController().navigate()
            is LoginActionState.Error -> showToast(loginActionState.errorMessage)
        }
    }

    private fun showToast(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }
}