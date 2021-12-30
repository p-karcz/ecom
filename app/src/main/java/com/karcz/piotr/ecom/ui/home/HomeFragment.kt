package com.karcz.piotr.ecom.ui.home

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.base.ui.BaseStateFragment
import com.karcz.piotr.ecom.common.ui.ProductsAdapter
import com.karcz.piotr.ecom.databinding.FragmentHomeBinding

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
            is HomeViewState.Error -> {}
            is HomeViewState.Loading -> {}
            is HomeViewState.Success -> {}
        }
    }

    override fun handleNavigation(navigation: HomeNavigation) {
        when(navigation) {

        }
    }

    private fun setUpListeners() {
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.homeMenuItem -> viewModel.onInteraction(HomeInteraction.HomeMenuItemClicked)
                R.id.cartMenuItem -> viewModel.onInteraction(HomeInteraction.CartMenuItemClicked)
                R.id.accountMenuItem -> viewModel.onInteraction(HomeInteraction.AccountMenuItemClicked)
                else -> throw Resources.NotFoundException()
            }
            true
        }
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
