package com.karcz.piotr.ecom.network.api

import com.karcz.piotr.ecom.network.Response
import com.karcz.piotr.ecom.network.transferdata.AddressTransferModel
import com.karcz.piotr.ecom.network.transferdata.CustomerTransferModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT

interface CustomerApi {

    @GET("/me")
    suspend fun getCustomer(): CustomerTransferModel

    @PUT("/me/update")
    suspend fun updateCustomer(@Body customerTransferModel: CustomerTransferModel): Response

    @DELETE("/me/removeAccount")
    suspend fun removeAccount(@Body customerTransferModel: CustomerTransferModel): Response

    @GET("/me/address")
    suspend fun getAddress(): AddressTransferModel

    @PUT("/me/address")
    suspend fun updateAddress(@Body addressTransferModel: AddressTransferModel): Response
}
