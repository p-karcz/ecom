package com.karcz.piotr.ecom.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.launch

abstract class BaseStateFragment<B: ViewBinding, VS, E, I>(
    viewBindingInflater: ViewBindingInflater<B>
) : BaseBindingFragment<B>(viewBindingInflater) {

    protected fun observeViewState(viewModel: BaseViewModel<VS, E, I>) {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    handleViewState(viewState)
                }
            }
        }
    }

    protected fun observeEvent(viewModel: BaseViewModel<VS, E, I>) {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { navigation ->
                    handleEvent(navigation)
                }
            }
        }
    }

    protected abstract fun handleViewState(viewState: VS)
    protected abstract fun handleEvent(event: E)
}
