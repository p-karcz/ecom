package com.karcz.piotr.ecom.network.api

import com.karcz.piotr.ecom.network.Response
import com.karcz.piotr.ecom.network.transferdata.AllCartsTransferModel
import com.karcz.piotr.ecom.network.transferdata.CartTransferModel
import retrofit2.http.*

interface CartApi {

    @GET("/me/cart")
    suspend fun getCartProducts(): AllCartsTransferModel

    @POST("/me/cart/addItem")
    suspend fun addProductsToCart(@Body cartTransferModel: CartTransferModel): Response

    @DELETE("/me/cart/removeItem")
    suspend fun deleteItemFromCart(@Body cartTransferModel: CartTransferModel): Response

    @PUT("/me/cart/updateItem")
    suspend fun updateItemInCart(@Body cartTransferModel: CartTransferModel): Response
}
