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
) : BaseViewModel<HomeViewState, HomeEvent, HomeInteraction>(HomeViewState.INITIAL) {

    init {
        refreshViewState()
    }

    override fun onInteraction(interaction: HomeInteraction) {
        when (interaction) {
            is HomeInteraction.SeeAllProductsCategoryClicked -> seeAllProductsFromCategory()
            is HomeInteraction.SeeAllProductsClicked -> seeAllProducts()
            is HomeInteraction.CategoryTitleClicked -> changeCategory(interaction.category)
            is HomeInteraction.ProductClicked -> seeProduct(interaction.id)
        }
    }

    private fun seeProduct(id: Int) {
        viewModelScope.launch {
            _event.emit(HomeEvent.NavigateToProductFragment(id))
        }
    }

    private fun seeAllProducts() {
        viewModelScope.launch {
            _event.emit(HomeEvent.NavigateToProductsFragment(EMPTY_STRING))
        }
    }

    private fun seeAllProductsFromCategory() {
        val activeCategory: String = _viewState.value.categories.first { it.second }.first
        viewModelScope.launch {
            _event.emit(HomeEvent.NavigateToProductsFragment(activeCategory))
        }
    }

    private fun changeCategory(category: Pair<String, Boolean>) {
        if (category.second) return
        val newCategories: List<Pair<String, Boolean>> =
            viewState.value.categories.map { it.first }.map { Pair(it, it == category.first) }
        val newCategoryProducts: List<ProductDomainModel> =
            viewState.value.products.filter { it.category == category.first }

        when (viewState.value) {
            HomeViewState.Loading -> {}
            is HomeViewState.NetworkError -> {
                _viewState.value = HomeViewState.NetworkError(
                    products = viewState.value.products,
                    categories = newCategories,
                    categoryProducts = newCategoryProducts
                )
            }
            is HomeViewState.Success -> {
                _viewState.value = HomeViewState.Success(
                    products = viewState.value.products,
                    categories = newCategories,
                    categoryProducts = newCategoryProducts
                )
            }
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
                        val newProducts = products.data
                        val newCategoriesTitles = extractCategoryTitles(newProducts)
                        val newCategoryProducts =
                            newProducts.filter { it.category == newCategoriesTitles.first().first }
                        _viewState.value = HomeViewState.Success(
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

                        _event.emit(HomeEvent.InternetError)
                    }
                    else -> {
                        throw IllegalStateException("Unexpected Resource type: ${products.javaClass}")
                    }
                }
            }
        }
    }

    companion object {
        const val EMPTY_STRING = ""
    }
}
