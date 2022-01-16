package com.karcz.piotr.ecom.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<VS, N, I>(initialViewState: VS) : ViewModel() {

    protected val _viewState: MutableStateFlow<VS> = MutableStateFlow(initialViewState)
    val viewState: StateFlow<VS> = _viewState.asStateFlow()

    protected val _navigation: MutableSharedFlow<N> = MutableSharedFlow()
    val navigation: SharedFlow<N> = _navigation.asSharedFlow()

    abstract fun onInteraction(interaction: I)
}
