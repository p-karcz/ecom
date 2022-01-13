package com.karcz.piotr.ecom.network.api

import com.karcz.piotr.ecom.data.ProductModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("/products/{productId}")
    suspend fun getProduct(@Query("productId") productId: Int): ProductModel

    @GET("/products")
    suspend fun getProducts(): List<ProductModel>
}
