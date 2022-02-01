package com.karcz.piotr.ecom.data.api

import com.karcz.piotr.ecom.data.transfer.AllProductsTransferModel
import com.karcz.piotr.ecom.data.transfer.ProductTransferModel
import com.karcz.piotr.ecom.data.transfer.ProductsFilterTransferModel
import com.karcz.piotr.ecom.data.transfer.SingleListTransferModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApi {

    @GET("/products/{productId}")
    suspend fun getProduct(@Query("productId") productId: Int): Response<ProductTransferModel>

    @GET("/products/{productId}/sizes")
    suspend fun getProductSizes(@Query("productId") productId: Int): Response<AllProductsTransferModel>

    @GET("/products")
    suspend fun getProducts(): Response<AllProductsTransferModel>

    @POST("/products")
    suspend fun getProducts(@Body productsFilterTransferModel: ProductsFilterTransferModel): Response<AllProductsTransferModel>

    @GET("/categories")
    suspend fun getCategories(): Response<SingleListTransferModel<String>>

    @GET("/producers")
    suspend fun getProducers(): Response<SingleListTransferModel<String>>

    @GET("/sizes")
    suspend fun getSizes(): Response<SingleListTransferModel<String>>

    @GET("/colors")
    suspend fun getColors(): Response<SingleListTransferModel<String>>
}
