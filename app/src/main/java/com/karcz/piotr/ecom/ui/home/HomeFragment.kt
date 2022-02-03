package com.karcz.piotr.ecom.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.databinding.FragmentHomeBinding
import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseStateFragment<FragmentHomeBinding, HomeViewState, HomeEvent, HomeInteraction>(
        FragmentHomeBinding::inflate
    ) {
    private val productsAdapter: ProductsAdapter = ProductsAdapter(::productOnClick)
    private val categoriesTitlesAdapter: CategoriesTitlesAdapter =
        CategoriesTitlesAdapter(::categoryOnClick)
    private val categoryProductsAdapter: CategoryProductsAdapter =
        CategoryProductsAdapter(::productOnClick)

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerViews()
        setUpListeners()
        observeViewState(viewModel)
        observeEvent(viewModel)
    }

    override fun handleViewState(viewState: HomeViewState) {
        when (viewState) {
            is HomeViewState.Success -> {
                binding.progressBar.visibility = View.GONE
                productsAdapter.submitList(viewState.products)
                categoriesTitlesAdapter.submitList(viewState.categories)
                categoryProductsAdapter.submitList(viewState.categoryProducts)
            }
            is HomeViewState.NetworkError -> {
                binding.progressBar.visibility = View.GONE
                productsAdapter.submitList(viewState.products)
                categoriesTitlesAdapter.submitList(viewState.categories)
                categoryProductsAdapter.submitList(viewState.categoryProducts)
            }
            is HomeViewState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.NavigateToProductFragment -> {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToProductFragment(event.id)
                findNavController().navigate(action)
            }
            is HomeEvent.NavigateToProductsFragment -> {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToProductsFragment(event.category)
                findNavController().navigate(action)
            }
            HomeEvent.InternetError -> {
                Snackbar.make(binding.root, R.string.network_error, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.snackbar_ok) {}
                    .setAnchorView(R.id.bottomNavigationView)
                    .show()
            }
        }
    }

    private fun categoryOnClick(category: Pair<String, Boolean>) {
        viewModel.onInteraction(HomeInteraction.CategoryTitleClicked(category))
    }

    private fun productOnClick(id: Int) {
        viewModel.onInteraction(HomeInteraction.ProductClicked(id))
    }

    private fun setUpListeners() {
        with(binding) {
            categoriesSeeAllTextView.setOnClickListener {
                viewModel.onInteraction(HomeInteraction.SeeAllProductsCategoryClicked)
            }
            productsSeeAllTextView.setOnClickListener {
                viewModel.onInteraction(HomeInteraction.SeeAllProductsClicked)
            }
        }
    }

    private fun setUpRecyclerViews() {
        with(binding) {
            productsRecyclerView.adapter = productsAdapter
            productsRecyclerView.layoutManager =
                GridLayoutManager(context, SPAN_COUNT, GridLayoutManager.VERTICAL, false)

            categoryProductsRecyclerView.adapter = categoryProductsAdapter
            categoryProductsRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            categoriesTitlesRecyclerView.adapter = categoriesTitlesAdapter
            categoriesTitlesRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    companion object {
        const val SPAN_COUNT = 2
    }
}
