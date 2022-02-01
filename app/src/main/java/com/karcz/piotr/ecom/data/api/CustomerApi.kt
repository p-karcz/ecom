package com.karcz.piotr.ecom.data.api

import com.karcz.piotr.ecom.data.transfer.AddressTransferModel
import com.karcz.piotr.ecom.data.transfer.CustomerTransferModel
import com.karcz.piotr.ecom.data.transfer.ServerGenericResponse
import retrofit2.Response
import retrofit2.http.*

interface CustomerApi {

    @GET("/me")
    suspend fun getCustomer(
        @Header("Authorization") token: String
    ): Response<CustomerTransferModel>

    @PUT("/me/update")
    suspend fun updateCustomer(
        @Header("Authorization") token: String,
        @Body customerTransferModel: CustomerTransferModel
    ): Response<ServerGenericResponse>

    @DELETE("/me/removeAccount")
    suspend fun removeAccount(
        @Header("Authorization") token: String,
        @Body customerTransferModel: CustomerTransferModel
    ): Response<ServerGenericResponse>

    @GET("/me/address")
    suspend fun getAddress(
        @Header("Authorization") token: String
    ): Response<AddressTransferModel>

    @PUT("/me/address")
    suspend fun updateAddress(
        @Header("Authorization") token: String,
        @Body addressTransferModel: AddressTransferModel
    ): Response<ServerGenericResponse>
}
