package com.karcz.piotr.ecom.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ProductsViewModelFactory {
    fun create(category: String): ProductsViewModel
}

fun provideProductsViewModelFactory(
    assistedFactory: ProductsViewModelFactory,
    initialCategory: String = ""
): ViewModelProvider.Factory =
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(initialCategory) as T
        }
    }
