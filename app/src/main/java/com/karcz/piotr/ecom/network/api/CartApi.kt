package com.karcz.piotr.ecom.network.api

import com.karcz.piotr.ecom.data.CartModel
import com.karcz.piotr.ecom.network.data.Response
import retrofit2.http.*

interface CartApi {

    @GET("/me/cart")
    suspend fun getCartProducts(): List<CartModel>

    @POST("/me/cart/addItem")
    suspend fun addProductsToCart(@Body cartModel: CartModel): Response

    @DELETE("/me/cart/removeItem")
    suspend fun deleteItemFromCart(@Body cartModel: CartModel): Response

    @PUT("/me/cart/updateItem")
    suspend fun updateItemInCart(@Body cartModel: CartModel): Response
}
