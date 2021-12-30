package com.karcz.piotr.ecom.network.api

import com.karcz.piotr.ecom.data.AddressModel
import com.karcz.piotr.ecom.data.CustomerModel
import com.karcz.piotr.ecom.network.data.Response
import retrofit2.http.*

interface CustomerApi {

    @GET("/me")
    suspend fun getCustomer(): CustomerModel

    @PUT("/me/update")
    suspend fun updateCustomer(@Body customerModel: CustomerModel): Response

    @DELETE("/me/removeAccount")
    suspend fun removeAccount(@Body customerModel: CustomerModel): Response

    @GET("/me/address")
    suspend fun getAddress(): AddressModel

    @POST("/me/address")
    suspend fun addAddress(@Body addressModel: AddressModel): Response

    @PUT("/me/address")
    suspend fun updateAddress(@Body addressModel: AddressModel): Response

    @DELETE("/me/address")
    suspend fun removeAddress(@Body addressModel: AddressModel): Response
}
