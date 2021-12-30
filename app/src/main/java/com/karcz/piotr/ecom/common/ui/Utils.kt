package com.karcz.piotr.ecom.common.ui

import android.view.View

fun View.visibleOrGone(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE else View.GONE
}
