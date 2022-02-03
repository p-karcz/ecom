package com.karcz.piotr.ecom.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.databinding.FragmentProductsBinding
import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment :
    BaseStateFragment<FragmentProductsBinding, ProductsViewState, ProductsEvent, ProductsInteraction>(
        FragmentProductsBinding::inflate
    ) {

    private val categoriesTitlesAdapter = CategoriesTitlesAdapter(::categoryOnClick)
    private val productsAdapter = ProductsAdapter(::productOnClick)

    private val viewModel: ProductsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpRecyclerViews()
        observeViewState(viewModel)
        observeEvent(viewModel)
        val category = arguments?.getString("category")
        viewModel.onInteraction(ProductsInteraction.ArgumentPassed(category))
    }

    override fun handleViewState(viewState: ProductsViewState) {
        when (viewState) {
            ProductsViewState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is ProductsViewState.NetworkError -> {
                binding.progressBar.visibility = View.GONE
                productsAdapter.submitList(viewState.products)
                categoriesTitlesAdapter.submitList(viewState.categories)
            }
            is ProductsViewState.Success -> {
                binding.progressBar.visibility = View.GONE
                productsAdapter.submitList(viewState.products)
                categoriesTitlesAdapter.submitList(viewState.categories)
            }
        }
    }

    override fun handleEvent(event: ProductsEvent) {
        when (event) {
            ProductsEvent.InternetError -> {
                Snackbar.make(binding.root, R.string.network_error, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.snackbar_ok) {}
                    .setAnchorView(R.id.bottomNavigationView)
                    .show()
            }
            is ProductsEvent.NavigateToProductFragment -> {
                val action =
                    ProductsFragmentDirections.actionProductsFragmentToProductFragment(event.id)
                findNavController().navigate(action)
            }
        }
    }

    private fun categoryOnClick(category: Pair<String, Boolean>) {
        viewModel.onInteraction(ProductsInteraction.CategoryTitleClicked(category))
    }

    private fun productOnClick(id: Int) {
        viewModel.onInteraction(ProductsInteraction.ProductClicked(id))
    }

    private fun setUpRecyclerViews() {
        with(binding) {
            categoriesTitlesRecyclerView.adapter = categoriesTitlesAdapter
            categoriesTitlesRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            productsRecyclerView.adapter = productsAdapter
            productsRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}
