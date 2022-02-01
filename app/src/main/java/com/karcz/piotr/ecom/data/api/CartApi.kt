package com.karcz.piotr.ecom.data.api

import com.karcz.piotr.ecom.data.transfer.AllCartsTransferModel
import com.karcz.piotr.ecom.data.transfer.CartTransferModel
import com.karcz.piotr.ecom.data.transfer.ServerGenericResponse
import retrofit2.Response
import retrofit2.http.*

interface CartApi {

    @GET("/me/cart")
    suspend fun getCartProducts(
        @Header("Authorization") token: String
    ): Response<AllCartsTransferModel>

    @POST("/me/cart/addItem")
    suspend fun addProductsToCart(
        @Header("Authorization") token: String,
        @Body cartTransferModel: CartTransferModel
    ): Response<ServerGenericResponse>

    @DELETE("/me/cart/removeItem")
    suspend fun deleteItemFromCart(
        @Header("Authorization") token: String,
        @Body cartTransferModel: CartTransferModel
    ): Response<ServerGenericResponse>

    @PUT("/me/cart/updateItem")
    suspend fun updateItemInCart(
        @Header("Authorization") token: String,
        @Body cartTransferModel: CartTransferModel
    ): Response<ServerGenericResponse>
}
