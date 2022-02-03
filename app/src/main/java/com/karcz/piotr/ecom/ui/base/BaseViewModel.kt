package com.karcz.piotr.ecom.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<VS, E, I>(initialViewState: VS) : ViewModel() {

    protected val _viewState: MutableStateFlow<VS> = MutableStateFlow(initialViewState)
    val viewState: StateFlow<VS> = _viewState.asStateFlow()

    protected val _event: MutableSharedFlow<E> = MutableSharedFlow()
    val event: SharedFlow<E> = _event.asSharedFlow()

    abstract fun onInteraction(interaction: I)
}
