package com.karcz.piotr.ecom.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.base.ui.BaseBindingFragment
import com.karcz.piotr.ecom.data.domain.ProductModel
import com.karcz.piotr.ecom.databinding.FragmentHomeBinding

class HomeFragment : BaseBindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val adapter: ProductsAdapter = ProductsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dummyList = listOf(
            ProductModel.SmallPicture(
                "Male img",
                ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.cat, null)
            ),
            ProductModel.BigPicture(
                ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.cat, null)
            )
        )
        setHasOptionsMenu(true)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.submitList(dummyList)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)
        return super.onOptionsItemSelected(item)
    }
}