package com.karcz.piotr.ecom.ui.products

import androidx.lifecycle.viewModelScope
import com.karcz.piotr.ecom.data.Resource
import com.karcz.piotr.ecom.data.domain.ProductDomainModel
import com.karcz.piotr.ecom.data.repository.ProductRepository
import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel<ProductsViewState, ProductsEvent, ProductsInteraction>(
    ProductsViewState.INITIAL
) {
    init {
        refreshViewState()
    }

    override fun onInteraction(interaction: ProductsInteraction) {
        when (interaction) {
            is ProductsInteraction.CategoryTitleClicked -> { }
            is ProductsInteraction.ProductClicked -> seeProduct(interaction.id)
            is ProductsInteraction.ArgumentPassed -> { }
        }
    }

    private fun seeProduct(id: Int) {
        viewModelScope.launch {
            _event.emit(ProductsEvent.NavigateToProductFragment(id))
        }
    }

    private fun extractCategoryTitles(products: List<ProductDomainModel>): List<Pair<String, Boolean>> {
        return products.map { it.category }.distinct().map { category ->
            Pair(category, false)
        }
    }

    private fun refreshViewState() {
        _viewState.value = ProductsViewState.Loading
        viewModelScope.launch {
            productRepository.getProducts().collect { products ->
                when (products) {
                    is Resource.Cached -> {
                        val newProducts = products.data
                        val newCategoriesTitles = extractCategoryTitles(newProducts)
                        val newCategoryProducts =
                            newProducts.filter { it.category == newCategoriesTitles.first().first }
                        _viewState.value = ProductsViewState.Success(
                            products = newProducts,
                            categories = newCategoriesTitles,
                            categoryProducts = newCategoryProducts
                        )
                    }
                    is Resource.NetworkSuccess -> {
                        val newProducts = products.data
                        val newCategoriesTitles = extractCategoryTitles(newProducts)
                        val newCategoryProducts =
                            newProducts.filter { it.category == newCategoriesTitles.first().first }
                        _viewState.value = ProductsViewState.Success(
                            products = newProducts,
                            categories = newCategoriesTitles,
                            categoryProducts = newCategoryProducts
                        )
                    }
                    is Resource.NetworkError -> {
                        if (viewState.value !is ProductsViewState.NetworkError) {
                            _event.emit(ProductsEvent.InternetError)
                        }

                        _viewState.value = ProductsViewState.NetworkError(
                            products = viewState.value.products,
                            categories = viewState.value.categories,
                            categoryProducts = viewState.value.categoryProducts
                        )
                    }
                    else -> {
                        throw IllegalStateException("Unexpected Resource type: ${products.javaClass}")
                    }
                }
            }
        }
    }
}
