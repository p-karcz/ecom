package com.karcz.piotr.ecom.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseStateFragment<B: ViewBinding, VS, N, I>(
    viewBindingInflater: ViewBindingInflater<B>
) : BaseBindingFragment<B>(viewBindingInflater) {

    protected fun observeViewState(viewModel: BaseViewModel<VS, N, I>) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    handleViewState(viewState)
                }
            }
        }
    }

    protected fun observeNavigation(viewModel: BaseViewModel<VS, N, I>) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigation.collect { navigation ->
                    handleNavigation(navigation)
                }
            }
        }
    }

    protected abstract fun handleViewState(viewState: VS)
    protected abstract fun handleNavigation(navigation: N)
}
