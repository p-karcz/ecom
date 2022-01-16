package com.karcz.piotr.ecom.data.database

import androidx.room.*
import com.karcz.piotr.ecom.data.entity.ProductEntityModel

@Dao
abstract class ProductDao {

    @Query("SELECT * FROM productsTable")
    abstract suspend fun getProducts(): List<ProductEntityModel>

    @Query("SELECT * FROM productsTable WHERE category IN(:categories) AND price > :priceFrom AND price < :priceTo AND producer IN(:producers) AND size IN(:sizes) AND color IN(:colors)")
    abstract suspend fun getProductsFiltered(
        categories: List<String>,
        priceFrom: Double,
        priceTo: Double,
        producers: List<String>,
        sizes: List<String>,
        colors: List<String>
    ): List<ProductEntityModel>

    @Query("SELECT * FROM productsTable WHERE id = :id")
    abstract suspend fun getProduct(id: Int): ProductEntityModel

    @Query("SELECT * FROM productsTable WHERE productCode = :productCode AND color = :color")
    abstract suspend fun getOtherSizesForProduct(productCode: Int, color: String): List<ProductEntityModel>

    @Query("SELECT category FROM productsTable")
    abstract suspend fun getCategories(): List<String>

    @Query("SELECT producer FROM productsTable")
    abstract suspend fun getProducers(): List<String>

    @Query("SELECT size FROM productsTable")
    abstract suspend fun getSizes(): List<String>

    @Query("SELECT color FROM productsTable")
    abstract suspend fun getColors(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProduct(product: ProductEntityModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertProducts(products: List<ProductEntityModel>)

    @Query("DELETE FROM productsTable")
    abstract suspend fun removeProducts()

    @Transaction
    suspend fun repopulateCache(products: List<ProductEntityModel>) {
        removeProducts()
        insertProducts(products)
    }
}
