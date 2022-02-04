package com.karcz.piotr.ecom.ui.products

import androidx.lifecycle.viewModelScope
import com.karcz.piotr.ecom.data.Resource
import com.karcz.piotr.ecom.data.domain.ProductDomainModel
import com.karcz.piotr.ecom.data.repository.ProductRepository
import com.karcz.piotr.ecom.ui.base.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductsViewModel @AssistedInject constructor(
    @Assisted private val initialCategory: String,
    private val productRepository: ProductRepository
) : BaseViewModel<ProductsViewState, ProductsEvent, ProductsInteraction>(
    ProductsViewState.INITIAL
) {
    private var fetchedProducts: List<ProductDomainModel> = emptyList()

    init {
        setUpInitialCategory(refreshViewState())
    }

    override fun onInteraction(interaction: ProductsInteraction) {
        when (interaction) {
            is ProductsInteraction.CategoryTitleClicked -> handleCategoryTitleClick(interaction.category)
            is ProductsInteraction.ProductClicked -> seeProduct(interaction.id)
        }
    }

    private fun updateViewState(
        products: List<ProductDomainModel>,
        categories: List<Pair<String, Boolean>>
    ) {
        when (viewState.value) {
            is ProductsViewState.Loading -> {}
            is ProductsViewState.NetworkError -> {
                _viewState.value = ProductsViewState.NetworkError(
                    products = products,
                    categories = categories
                )
            }
            is ProductsViewState.Success -> {
                _viewState.value = ProductsViewState.Success(
                    products = products,
                    categories = categories
                )
            }
        }
    }

    private fun filterProductsWithCategories(categories: List<Pair<String, Boolean>>): List<ProductDomainModel> {
        return fetchedProducts.filter { fetchedProduct ->
            fetchedProduct.category in categories.filter { it.second }
                .map { it.first }
        }
    }

    private fun setUpInitialCategory(job: Job) {
        viewModelScope.launch {
            job.join()
            val allCategories = fetchedProducts.map { it.category }.distinct()
            val initialCategories = if (initialCategory.isNotEmpty()) {
                allCategories.map { Pair(it, it == initialCategory) }
            } else {
                allCategories.map { Pair(it, true) }
            }
            val initialProducts = filterProductsWithCategories(initialCategories)

            updateViewState(initialProducts, initialCategories)
        }
    }

    private fun handleCategoryTitleClick(category: Pair<String, Boolean>) {
        val newCategories = viewState.value.categories.map {
            if (it.first != category.first) {
                it
            } else {
                Pair(
                    category.first,
                    !category.second
                )
            }
        }
        val newProducts = filterProductsWithCategories(newCategories)

        updateViewState(newProducts, newCategories)
    }

    private fun seeProduct(id: Int) {
        viewModelScope.launch {
            _event.emit(ProductsEvent.NavigateToProductFragment(id))
        }
    }

    private fun extractCategories(products: List<ProductDomainModel>): List<Pair<String, Boolean>> {
        val distinctNewCategoriesTitles = products.map { it.category }.distinct()
        val savedCategories =
            viewState.value.categories.filter { it.first in distinctNewCategoriesTitles }
        val newFetchedCategories =
            distinctNewCategoriesTitles.filter { newCategoryTitle -> newCategoryTitle !in savedCategories.map { it.first } }
        return savedCategories + newFetchedCategories.map { Pair(it, false) }
    }

    private fun refreshViewState(): Job {
        _viewState.value = ProductsViewState.Loading
        return viewModelScope.launch {
            productRepository.getProducts().collect { productsResource ->
                when (productsResource) {
                    is Resource.Cached -> {
                        fetchedProducts = productsResource.data
                        val newCategories = extractCategories(productsResource.data)
                        _viewState.value = ProductsViewState.Success(
                            products = filterProductsWithCategories(newCategories),
                            categories = newCategories
                        )
                    }
                    is Resource.NetworkSuccess -> {
                        fetchedProducts = productsResource.data
                        val newCategories = extractCategories(productsResource.data)
                        _viewState.value = ProductsViewState.Success(
                            products = filterProductsWithCategories(newCategories),
                            categories = newCategories
                        )
                    }
                    is Resource.NetworkError -> {
                        if (viewState.value !is ProductsViewState.NetworkError) {
                            _event.emit(ProductsEvent.InternetError)
                        }

                        _viewState.value = ProductsViewState.NetworkError(
                            products = viewState.value.products,
                            categories = viewState.value.categories
                        )
                    }
                    else -> {
                        throw IllegalStateException("Unexpected Resource type: ${productsResource.javaClass}")
                    }
                }
            }
        }
    }
}
