package com.karcz.piotr.ecom.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.karcz.piotr.ecom.databinding.FragmentHomeBinding
import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import com.karcz.piotr.ecom.ui.common.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseStateFragment<FragmentHomeBinding, HomeViewState, HomeNavigation, HomeInteraction>(
    FragmentHomeBinding::inflate
) {

    private val productsAdapter: ProductsAdapter = ProductsAdapter()
    private val categoryProductsAdapter: CategoryProductsAdapter = CategoryProductsAdapter()
    private val categoryTitlesAdapter: CategoryTitlesAdapter = CategoryTitlesAdapter()

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerViews()
        setUpListeners()
        observeViewState(viewModel)
        observeNavigation(viewModel)
    }

    override fun handleViewState(viewState: HomeViewState) {
        when(viewState) {
            is HomeViewState.Success -> {}
            is HomeViewState.CachedSuccess -> {}
            is HomeViewState.Loading -> {}
            is HomeViewState.NetworkError -> {}
            is HomeViewState.Unauthorized -> {}
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
            productsRecyclerView.layoutManager = GridLayoutManager(context, 2)

            categoryProductsRecyclerView.adapter = categoryProductsAdapter
            categoryProductsRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            categoryTitlesRecyclerView.adapter = categoryTitlesAdapter
            categoryTitlesRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}
