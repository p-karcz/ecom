package com.karcz.piotr.ecom.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.base.ui.BaseBindingFragment
import com.karcz.piotr.ecom.data.domain.ProductModel
import com.karcz.piotr.ecom.databinding.FragmentHomeBinding

class HomeFragment : BaseBindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val adapter: ProductsAdapter = ProductsAdapter()
    private val dummyList = listOf(
        ProductModel.SmallPicture(
            "Male img",
            ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_foreground, null)
        ),
        ProductModel.BigPicture(
            ResourcesCompat.getDrawable(resources, R.drawable.ic_launcher_background, null)
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.adapter = adapter
        adapter.submitList(dummyList)
    }
}