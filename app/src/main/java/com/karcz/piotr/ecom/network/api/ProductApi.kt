package com.karcz.piotr.ecom.network.api

import com.karcz.piotr.ecom.data.CategoryModel
import com.karcz.piotr.ecom.data.ProductModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("/products/{productId}")
    suspend fun getProduct(@Query("productId") productId: Int): ProductModel

    @GET("/products")
    suspend fun getProducts(): List<ProductModel>

    @GET("/categories")
    suspend fun getCategories(): List<CategoryModel>

    @GET("/products/{categoryName}")
    suspend fun getProductsByCategory(@Path("categoryName") categoryName: String): List<ProductModel>

    @GET("/{categoryName}")
    suspend fun getCategory(@Path("categoryName") categoryName: String): CategoryModel
}
