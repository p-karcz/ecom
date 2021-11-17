package com.karcz.piotr.ecom.data.domain

import android.graphics.drawable.Drawable

sealed class ProductModel {
    data class SmallPicture(val text: String, val image: Drawable?): ProductModel()
    data class BigPicture(val image: Drawable?): ProductModel()
}
