package com.karcz.piotr.ecom.network.api

import com.karcz.piotr.ecom.data.OrderDetailModel
import com.karcz.piotr.ecom.data.OrderModel
import com.karcz.piotr.ecom.network.data.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrdersApi {

    @GET("me/orders")
    suspend fun getOrders(): List<OrderModel>

    @POST("/me/orders")
    suspend fun addOrder(@Body orderModel: OrderModel): Response

    @GET("me/orders/{orderId}")
    suspend fun getOrder(@Path("orderId") orderId: Int): List<OrderDetailModel>
}
