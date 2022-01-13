package com.karcz.piotr.ecom.network.api

import com.karcz.piotr.ecom.network.transferdata.CustomerLoginTransferModel
import com.karcz.piotr.ecom.network.transferdata.CustomerRegistrationTransferModel
import com.karcz.piotr.ecom.network.transferdata.TokenTransferModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationApi {

    @POST("/login")
    suspend fun login(@Body customerLoginTransferModel: CustomerLoginTransferModel): TokenTransferModel

    @POST("/register")
    suspend fun register(@Body customerRegistrationTransferModel: CustomerRegistrationTransferModel): TokenTransferModel
}
