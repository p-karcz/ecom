package com.karcz.piotr.ecom.ui.base

import android.view.View

fun View.visibleOrGone(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE else View.GONE
}
