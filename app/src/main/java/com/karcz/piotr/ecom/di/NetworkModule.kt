package com.karcz.piotr.ecom.di

import com.google.gson.GsonBuilder
import com.karcz.piotr.ecom.data.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://24aa-78-10-211-109.ngrok.io/"
    private val converterFactory = GsonConverterFactory.create(GsonBuilder().create())

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    fun provideAuthorizationApi(): AuthorizationApi {
        return retrofit.create(AuthorizationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCartApi(): CartApi {
        return retrofit.create(CartApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCustomerApi(): CustomerApi {
        return retrofit.create(CustomerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOrdersApi(): OrdersApi {
        return retrofit.create(OrdersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }
}
