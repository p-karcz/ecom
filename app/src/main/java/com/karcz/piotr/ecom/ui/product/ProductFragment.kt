package com.karcz.piotr.ecom.ui.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.databinding.FragmentProductBinding
import com.karcz.piotr.ecom.ui.base.BaseStateFragment
import com.karcz.piotr.ecom.ui.base.displaySnackBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductFragment :
    BaseStateFragment<FragmentProductBinding, ProductViewState, ProductEvent, ProductInteraction>(
        FragmentProductBinding::inflate
    ) {

    @Inject
    lateinit var productViewModelFactory: ProductViewModelFactory

    private val viewModel: ProductViewModel by viewModels {
        provideProductViewModelFactory(
            productViewModelFactory,
            arguments?.getInt("id") ?: 0
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewState(viewModel)
        observeEvent(viewModel)
        setOnClickListeners()
    }

    override fun handleViewState(viewState: ProductViewState) {
        with(binding) {
            when (viewState) {
                is ProductViewState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is ProductViewState.NetworkError -> {
                    progressBar.visibility = View.GONE
                    titleTextView.text = viewState.product?.name
                    priceTextView.text =
                        requireContext().getString(
                            R.string.price,
                            viewState.product?.price.toString()
                        )
                    descriptionTextView.text = viewState.product?.description
                }
                is ProductViewState.Success -> {
                    progressBar.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    titleTextView.text = viewState.product?.name
                    priceTextView.text =
                        requireContext().getString(
                            R.string.price,
                            viewState.product?.price.toString()
                        )
                    descriptionTextView.text = viewState.product?.description
                }
            }
        }
    }

    override fun handleEvent(event: ProductEvent) {
        when (event) {
            ProductEvent.AuthorizationRequired -> {
                displaySnackBar(binding.root, R.string.authorization_required)
            }
            ProductEvent.ProductAddedSuccessfully -> {
                displaySnackBar(binding.root, R.string.product_added_to_cart, requireContext().getColor(R.color.success))
            }
            ProductEvent.AddToCartNetworkError -> {
                displaySnackBar(binding.root, R.string.add_to_cart_network_error, requireContext().getColor(R.color.error))
            }
            ProductEvent.LoadingProductNetworkError -> {
                displaySnackBar(binding.root, R.string.network_error)
            }
        }
    }

    private fun setOnClickListeners() {
        binding.buyButton.setOnClickListener {
            viewModel.onInteraction(ProductInteraction.BuyButtonClicked)
        }
    }
}
