package com.karcz.piotr.ecom.network.api

import com.karcz.piotr.ecom.network.Response
import com.karcz.piotr.ecom.network.transferdata.AllOrderDetailsTransferModel
import com.karcz.piotr.ecom.network.transferdata.AllOrderDetailsWithProductsTransferModel
import com.karcz.piotr.ecom.network.transferdata.AllOrdersTransferModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrdersApi {

    @GET("me/orders")
    suspend fun getOrders(): AllOrdersTransferModel

    @POST("/me/orders")
    suspend fun addOrder(@Body allOrderDetailsTransferModel: AllOrderDetailsTransferModel): Response

    @GET("me/orders/{orderId}")
    suspend fun getOrder(@Path("orderId") orderId: Int): AllOrderDetailsWithProductsTransferModel
}
