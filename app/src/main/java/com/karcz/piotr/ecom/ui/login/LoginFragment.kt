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
import com.karcz.piotr.ecom.base.ui.BaseStateFragment
import com.karcz.piotr.ecom.databinding.FragmentLoginBinding

class LoginFragment : BaseStateFragment<FragmentLoginBinding, LoginViewState, LoginNavigation, LoginInteraction>(
    FragmentLoginBinding::inflate
) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpOnClickListeners()
        observeViewState(viewModel)
        observeNavigation(viewModel)
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
        binding.loadingProgressBar.isVisible = viewState.isLoading
        binding.loginButton.isEnabled = viewState.isLoginButtonEnabled
    }

    override fun handleNavigation(navigation: LoginNavigation) = when (navigation) {
        is LoginNavigation.NavigateToHome ->
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        is LoginNavigation.NavigateToRegistration ->
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        is LoginNavigation.ShowToast -> showToast(navigation.text)
    }

    private fun showToast(errorMessage: String) =
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
}
