package com.karcz.piotr.ecom.ui.base

import android.view.View
import androidx.annotation.ColorInt
import com.google.android.material.snackbar.Snackbar
import com.karcz.piotr.ecom.R

fun View.visibleOrGone(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE else View.GONE
}

fun displaySnackBar(
    view: View,
    resIdText: Int,
    @ColorInt color: Int? = null
) {
    val snackBar = Snackbar.make(view, resIdText, Snackbar.LENGTH_INDEFINITE)
        .setAction(R.string.snackbar_ok) {}
        .setAnchorView(R.id.bottomNavigationView)

    color?.let { snackBar.setBackgroundTint(it) }

    snackBar.show()
}
