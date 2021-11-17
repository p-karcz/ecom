package com.karcz.piotr.ecom.ui.login

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.base.ui.BaseBindingFragment
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
            loginViewModel.onInteraction(LoginInteraction.RegisterButtonClicked)
        }

        binding.loginButton.setOnClickListener {
            loginViewModel.onInteraction(LoginInteraction.LoginButtonClicked)
        }

        binding.emailEditText.addTextChangedListener(afterTextChanged = ::checkEmail)
        binding.passwordEditText.addTextChangedListener(afterTextChanged = ::checkPassword)
    }

    private fun checkEmail(text: Editable?) =
        loginViewModel.onInteraction(LoginInteraction.EmailFieldChanged(text))

    private fun checkPassword(text: Editable?) =
        loginViewModel.onInteraction(LoginInteraction.PasswordFieldChanged(text))

    private fun observeViewState() = viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            loginViewModel.loginViewState.collect { loginViewState ->
                handleViewState(loginViewState)
            }
        }
    }

    private fun handleViewState(loginViewState: LoginViewState) {
        binding.loadingProgressBar.isVisible = loginViewState.isLoading
        binding.loginButton.isEnabled = loginViewState.isLoginButtonEnabled
    }

    private fun observeActionState() = viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            loginViewModel.loginActionState.collect { loginActionState ->
                handleActionState(loginActionState)
            }
        }
    }

    private fun handleActionState(loginActionState: LoginActionState) = when (loginActionState) {
        is LoginActionState.NavigateToHome ->
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        is LoginActionState.NavigateToRegistration ->
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        is LoginActionState.ShowToast -> showToast(loginActionState.text)
    }

    private fun showToast(errorMessage: String) =
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
}