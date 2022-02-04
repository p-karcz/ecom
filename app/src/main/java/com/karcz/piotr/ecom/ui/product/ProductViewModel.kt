package com.karcz.piotr.ecom.ui.product

import androidx.lifecycle.viewModelScope
import com.karcz.piotr.ecom.data.Resource
import com.karcz.piotr.ecom.data.domain.CartDomainModel
import com.karcz.piotr.ecom.data.repository.CartRepository
import com.karcz.piotr.ecom.data.repository.ProductRepository
import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class ProductViewModel @AssistedInject constructor(
    @Assisted private val id: Int,
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository
) : BaseViewModel<ProductViewState, ProductEvent, ProductInteraction>(
    ProductViewState.INITIAL
) {

    init {
        loadProduct()
    }

    override fun onInteraction(interaction: ProductInteraction) {
        when (interaction) {
            ProductInteraction.BuyButtonClicked -> addToCart()
        }
    }

    private fun addToCart() {
        viewModelScope.launch {
            cartRepository.addProductsToCart(
                CartDomainModel(
                    productId = id,
                    quantity = 1
                )
            ).collect { resourceResponse ->
                when(resourceResponse) {
                    is Resource.NetworkError -> _event.emit(ProductEvent.AddToCartNetworkError)
                    is Resource.NetworkSuccess -> {
                        if (resourceResponse.data.successful) {
                            _event.emit(ProductEvent.ProductAddedSuccessfully)
                        } else {
                            _event.emit(ProductEvent.AddToCartNetworkError)
                        }
                    }
                    is Resource.NetworkUnauthenticated -> _event.emit(ProductEvent.AuthorizationRequired)
                    else -> {
                        throw IllegalStateException("Unexpected Resource type: ${resourceResponse.javaClass}")
                    }
                }
            }
        }
    }

    private fun loadProduct() {
        _viewState.value = ProductViewState.Loading
        viewModelScope.launch {
            productRepository.getProduct(id).collect { productResource ->
                when (productResource) {
                    is Resource.Cached -> {
                        _viewState.value = ProductViewState.Success(productResource.data)
                    }
                    is Resource.NetworkError -> {
                        if (viewState.value !is ProductViewState.NetworkError) {
                            _event.emit(ProductEvent.LoadingProductNetworkError)
                        }

                        _viewState.value = ProductViewState.NetworkError(viewState.value.product)
                    }
                    is Resource.NetworkSuccess -> {
                        _viewState.value = ProductViewState.Success(productResource.data)
                    }
                    else -> {
                        throw IllegalStateException("Unexpected Resource type: ${productResource.javaClass}")
                    }
                }
            }
        }
    }
}
