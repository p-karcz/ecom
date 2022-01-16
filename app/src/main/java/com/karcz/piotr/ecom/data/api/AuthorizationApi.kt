package com.karcz.piotr.ecom.data.api

import com.karcz.piotr.ecom.data.transfer.CustomerLoginTransferModel
import com.karcz.piotr.ecom.data.transfer.CustomerRegistrationTransferModel
import com.karcz.piotr.ecom.data.transfer.TokenTransferModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationApi {

    @POST("/login")
    suspend fun login(@Body customerLoginTransferModel: CustomerLoginTransferModel): TokenTransferModel

    @POST("/register")
    suspend fun register(@Body customerRegistrationTransferModel: CustomerRegistrationTransferModel): TokenTransferModel
}
