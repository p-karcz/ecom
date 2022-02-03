package com.karcz.piotr.ecom.ui.home

import androidx.lifecycle.viewModelScope
import com.karcz.piotr.ecom.data.Resource
import com.karcz.piotr.ecom.data.domain.ProductDomainModel
import com.karcz.piotr.ecom.data.repository.ProductRepository
import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel<HomeViewState, HomeNavigation, HomeInteraction>(HomeViewState.INITIAL) {

    override fun onInteraction(interaction: HomeInteraction) {
        when (interaction) {
            is HomeInteraction.ScreenEntered -> refreshViewState()
        }
    }

    private fun extractCategoryTitles(products: List<ProductDomainModel>): List<Pair<String, Boolean>> {
        return products.map { it.category }.distinct().mapIndexed { index, category ->
            if (index == 0) {
                Pair(category, true)
            } else {
                Pair(category, false)
            }
        }
    }

    private fun refreshViewState() {
        _viewState.value = HomeViewState.Loading
        viewModelScope.launch {
            productRepository.getProducts().collect { products ->
                when (products) {
                    is Resource.Cached -> {
                        val newProducts = products.data.products
                        val newCategoriesTitles = extractCategoryTitles(newProducts)
                        val newCategoryProducts = newProducts.filter { it.category == newCategoriesTitles.first().first }
                        _viewState.value = HomeViewState.Success(
                            products = newProducts,
                            categories = newCategoriesTitles,
                            categoryProducts = newCategoryProducts
                        )
                    }
                    is Resource.NetworkSuccess -> {
                        val newProducts = products.data.products
                        val newCategoriesTitles = extractCategoryTitles(newProducts)
                        val newCategoryProducts = newProducts.filter { it.category == newCategoriesTitles.first().first }
                        _viewState.value = HomeViewState.Success(
                            products = newProducts,
                            categories = newCategoriesTitles,
                            categoryProducts = newCategoryProducts
                        )
                    }
                    is Resource.NetworkError -> {
                        _viewState.value = HomeViewState.NetworkError(
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
