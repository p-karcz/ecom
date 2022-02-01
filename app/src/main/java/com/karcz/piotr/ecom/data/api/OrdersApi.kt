package com.karcz.piotr.ecom.data.api

import com.karcz.piotr.ecom.data.transfer.AllOrderDetailsTransferModel
import com.karcz.piotr.ecom.data.transfer.AllOrderDetailsWithProductsTransferModel
import com.karcz.piotr.ecom.data.transfer.AllOrdersTransferModel
import com.karcz.piotr.ecom.data.transfer.ServerGenericResponse
import retrofit2.Response
import retrofit2.http.*

interface OrdersApi {

    @GET("me/orders")
    suspend fun getOrders(
        @Header("Authorization") token: String
    ): Response<AllOrdersTransferModel>

    @POST("/me/orders")
    suspend fun addOrder(
        @Header("Authorization") token: String,
        @Body allOrderDetailsTransferModel: AllOrderDetailsTransferModel
    ): Response<ServerGenericResponse>

    @GET("me/orders/{orderId}")
    suspend fun getOrder(
        @Header("Authorization") token: String,
        @Path("orderId") orderId: Int
    ): Response<AllOrderDetailsWithProductsTransferModel>
}
