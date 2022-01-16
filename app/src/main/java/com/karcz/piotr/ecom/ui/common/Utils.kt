package com.karcz.piotr.ecom.ui.common

import android.view.View

fun View.visibleOrGone(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE else View.GONE
}
