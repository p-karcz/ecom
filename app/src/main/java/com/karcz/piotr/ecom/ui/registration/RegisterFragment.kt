package com.karcz.piotr.ecom.ui.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import com.karcz.piotr.ecom.databinding.FragmentRegistrationBinding
import com.karcz.piotr.ecom.ui.base.displaySnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment
    :
    BaseStateFragment<FragmentRegistrationBinding, RegisterViewState, RegisterEvent, RegisterInteraction>(
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
                binding.progressBar.visibility = View.GONE
            }
            is RegisterViewState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun handleEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.NavigateToLogin -> findNavController().popBackStack()
            is RegisterEvent.Error -> displaySnackBar(
                binding.root,
                R.string.registration_error,
                requireContext().getColor(R.color.error)
            )
        }
    }

    private fun setUpOnClickListener() {
        with(binding) {
            registerButton.setOnClickListener {
                viewModel.onInteraction(
                    RegisterInteraction.RegisterButtonClicked(
                        email = emailEditText.text.toString(),
                        password = passwordEditText.text.toString(),
                        name = nameEditText.text.toString(),
                        surname = surnameEditText.text.toString(),
                        street = streetEditText.text.toString(),
                        streetNumber = streetNumberEditText.text.toString(),
                        flatNumber = flatNumberEditText.text.toString(),
                        postalCode = postalCodeEditText.text.toString(),
                        country = countryEditText.text.toString(),
                        city = cityEditText.text.toString()
                    )
                )
            }
        }
    }
}
