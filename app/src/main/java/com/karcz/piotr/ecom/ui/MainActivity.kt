package com.karcz.piotr.ecom.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.karcz.piotr.ecom.R
import com.karcz.piotr.ecom.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.cartFragment, R.id.accountFragment -> {
                    bottomNavigationView.menu.setGroupCheckable(0, true, true)
                    bottomNavigationView.isVisible = true
                }
                R.id.productFragment, R.id.productsFragment -> {
                    bottomNavigationView.menu.setGroupCheckable(0, false, true)
                    bottomNavigationView.isVisible = true
                }
                else -> {
                    bottomNavigationView.menu.setGroupCheckable(0, false, true)
                    bottomNavigationView.isVisible = false
                }
            }
        }
    }
}
