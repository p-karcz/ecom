package com.karcz.piotr.ecom.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ProductViewModelFactory {
    fun create(id: Int): ProductViewModel
}

fun provideProductViewModelFactory(
    assistedFactory: ProductViewModelFactory,
    id: Int = 0
): ViewModelProvider.Factory =
    object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(id) as T
        }
    }
