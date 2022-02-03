package com.karcz.piotr.ecom.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.databinding.FragmentHomeBinding
import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseStateFragment<FragmentHomeBinding, HomeViewState, HomeNavigation, HomeInteraction>(
    FragmentHomeBinding::inflate
) {
    private val productsAdapter: ProductsAdapter = ProductsAdapter()
    private val categoriesTitlesAdapter: CategoriesTitlesAdapter = CategoriesTitlesAdapter()
    private val categoryProductsAdapter: CategoryProductsAdapter = CategoryProductsAdapter()

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerViews()
        setUpListeners()
        observeViewState(viewModel)
        observeNavigation(viewModel)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onInteraction(HomeInteraction.ScreenEntered)
    }

    override fun handleViewState(viewState: HomeViewState) {
        when(viewState) {
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

                Snackbar.make(binding.root, R.string.network_error, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.snackbar_ok) {}
                    .setAnchorView(R.id.bottomNavigationView)
                    .show()
            }
            is HomeViewState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun handleNavigation(navigation: HomeNavigation) {
        when(navigation) {

        }
    }

    private fun setUpListeners() {

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
