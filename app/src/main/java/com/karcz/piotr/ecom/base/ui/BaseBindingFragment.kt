package com.karcz.piotr.ecom.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

private typealias ViewBindingInflater<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseBindingFragment<B: ViewBinding>(
    private val viewBindingInflater: ViewBindingInflater<B>
) : Fragment() {

    private var _binding: B? = null
    protected val binding: B
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = viewBindingInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}