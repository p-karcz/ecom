package com.karcz.piotr.ecom.ui.registration

sealed class RegisterInteraction {
    data class RegisterButtonClicked(
        val email: String?,
        val password: String?,
        val name: String?,
        val surname: String?,
        val street: String?,
        val streetNumber: String?,
        val flatNumber: String?,
        val postalCode: String?,
        val country: String?,
        val city: String?
    ) : RegisterInteraction()
}
