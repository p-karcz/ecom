package com.karcz.piotr.ecom.di

import android.content.Context
import com.karcz.piotr.ecom.data.database.ProductDao
import com.karcz.piotr.ecom.data.database.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideProductsDatabase(@ApplicationContext context: Context): ProductsDatabase {
        return ProductsDatabase.getDatabase(context)
    }

    @Provides
    fun provideProductDao(productsDatabase: ProductsDatabase) : ProductDao {
        return productsDatabase.productDao()
    }
}
