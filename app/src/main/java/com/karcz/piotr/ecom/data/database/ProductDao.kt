package com.karcz.piotr.ecom.data.database

import androidx.room.*
import com.karcz.piotr.ecom.data.entity.ProductEntityModel

@Dao
interface ProductDao {

    @Query("SELECT * FROM productsTable")
    suspend fun getProducts(): List<ProductEntityModel>

    @Query("SELECT * FROM productsTable WHERE category IN(:categories) AND price > :priceFrom AND price < :priceTo AND producer IN(:producers) AND size IN(:sizes) AND color IN(:colors)")
    suspend fun getProductsFiltered(
        categories: List<String>,
        priceFrom: Double,
        priceTo: Double,
        producers: List<String>,
        sizes: List<String>,
        colors: List<String>
    ): List<ProductEntityModel>

    @Query("SELECT * FROM productsTable WHERE id = :id")
    suspend fun getProduct(id: Int): ProductEntityModel

    @Query("SELECT * FROM productsTable WHERE productCode = :productCode AND color = :color")
    suspend fun getOtherSizesForProduct(productCode: Int, color: String): List<ProductEntityModel>

    @Query("SELECT category FROM productsTable")
    suspend fun getCategories(): List<String>

    @Query("SELECT producer FROM productsTable")
    suspend fun getProducers(): List<String>

    @Query("SELECT size FROM productsTable")
    suspend fun getSizes(): List<String>

    @Query("SELECT color FROM productsTable")
    suspend fun getColors(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntityModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntityModel>)

    @Query("DELETE FROM productsTable")
    suspend fun removeProducts()

    @Transaction
    suspend fun repopulateCache(products: List<ProductEntityModel>) {
        removeProducts()
        insertProducts(products)
    }
}
