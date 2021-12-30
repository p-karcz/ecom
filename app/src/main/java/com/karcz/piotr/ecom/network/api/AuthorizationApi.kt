package com.karcz.piotr.ecom.network.api

import com.karcz.piotr.ecom.data.CustomerModel
import com.karcz.piotr.ecom.network.data.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationApi {

    @POST("/login")
    suspend fun login(@Body customerModel: CustomerModel): Response

    @POST("/register")
    suspend fun register(@Body customerModel: CustomerModel): Response
}
