package com.karcz.piotr.ecom.ui.login

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.karcz.piotr.ecom.R
import androidx.navigation.fragment.findNavController
import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import com.karcz.piotr.ecom.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseStateFragment<FragmentLoginBinding, LoginViewState, LoginNavigation, LoginInteraction>(
    FragmentLoginBinding::inflate
) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
        observeViewState(viewModel)
        observeEvent(viewModel)
    }

    private fun setUpOnClickListeners() {
        binding.registerButton.setOnClickListener {
            viewModel.onInteraction(LoginInteraction.RegisterButtonClicked)
        }

        binding.loginButton.setOnClickListener {
            viewModel.onInteraction(LoginInteraction.LoginButtonClicked)
        }

        binding.emailEditText.addTextChangedListener(afterTextChanged = ::checkEmail)
        binding.passwordEditText.addTextChangedListener(afterTextChanged = ::checkPassword)
    }

    private fun checkEmail(text: Editable?) =
        viewModel.onInteraction(LoginInteraction.EmailFieldChanged(text))

    private fun checkPassword(text: Editable?) =
        viewModel.onInteraction(LoginInteraction.PasswordFieldChanged(text))

    override fun handleViewState(viewState: LoginViewState) {
        when(viewState) {
            is LoginViewState.Success -> {
                binding.loadingProgressBar.isVisible = false
                binding.loginButton.isEnabled = viewState.isLoginButtonEnabled
            }
            is LoginViewState.Loading ->
                binding.loadingProgressBar.isVisible = true
            is LoginViewState.Error -> { }
        }
    }

    override fun handleEvent(event: LoginNavigation) = when (event) {
        is LoginNavigation.NavigateToHome ->
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        is LoginNavigation.NavigateToRegistration ->
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        is LoginNavigation.ShowToast -> showToast(event.text)
    }

    private fun showToast(errorMessage: String) =
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
}
