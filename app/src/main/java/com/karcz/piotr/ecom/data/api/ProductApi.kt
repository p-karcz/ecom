package com.karcz.piotr.ecom.data.api

import com.karcz.piotr.ecom.data.transfer.AllProductsTransferModel
import com.karcz.piotr.ecom.data.transfer.ProductTransferModel
import com.karcz.piotr.ecom.data.transfer.ProductsFilterTransferModel
import com.karcz.piotr.ecom.data.transfer.SingleListTransferModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApi {

    @GET("/products/{productId}")
    suspend fun getProduct(@Query("productId") productId: Int): ProductTransferModel

    @GET("/products/{productId}/sizes")
    suspend fun getProductSizes(@Query("productId") productId: Int): AllProductsTransferModel

    @GET("/products")
    suspend fun getProducts(): AllProductsTransferModel

    @POST("/products")
    suspend fun getProducts(@Body productsFilterTransferModel: ProductsFilterTransferModel): AllProductsTransferModel

    @GET("/categories")
    suspend fun getCategories(): SingleListTransferModel<String>

    @GET("/producers")
    suspend fun getProducers(): SingleListTransferModel<String>

    @GET("/sizes")
    suspend fun getSizes(): SingleListTransferModel<String>

    @GET("/colors")
    suspend fun getColors(): SingleListTransferModel<String>
}
