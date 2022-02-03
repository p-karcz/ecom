package com.karcz.piotr.ecom.data.repository

import com.karcz.piotr.ecom.data.Resource
import com.karcz.piotr.ecom.data.api.CartApi
import com.karcz.piotr.ecom.data.database.UserTokenDataStore
import com.karcz.piotr.ecom.data.domain.AllCartsDomainModel
import com.karcz.piotr.ecom.data.domain.CartDomainModel
import com.karcz.piotr.ecom.data.transfer.ServerGenericResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartApi: CartApi,
    private val userTokenDataStore: UserTokenDataStore
) {

    fun getCartProducts(): Flow<Resource<AllCartsDomainModel>> {
        return flow {
            val cartResponse = try {
                val token = userTokenDataStore.getToken()
                if (token == null) {
                    emit(Resource.NetworkUnauthorized())
                    return@flow
                }
                cartApi.getCartProducts(token)
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                cartResponse.isSuccessful && cartResponse.body()?.toDomainModel() != null -> {
                    val cart = cartResponse.body()!!.toDomainModel()!!
                    emit(Resource.NetworkSuccess(cart))
                }
                !cartResponse.isSuccessful && cartResponse.code() == 401 ->
                    emit(Resource.NetworkUnauthorized())
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun addProductsToCart(cartDomainModel: CartDomainModel): Flow<Resource<ServerGenericResponse>> {
        return flow {
            val cartResponse = try {
                val token = userTokenDataStore.getToken()
                if (token == null) {
                    emit(Resource.NetworkUnauthorized())
                    return@flow
                }
                cartApi.addProductsToCart(
                    token = token,
                    cartTransferModel = cartDomainModel.toTransferModel()
                )
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                cartResponse.isSuccessful && cartResponse.body() != null -> {
                    val cart = cartResponse.body()!!
                    emit(Resource.NetworkSuccess(cart))
                }
                !cartResponse.isSuccessful && cartResponse.code() == 401 ->
                    emit(Resource.NetworkUnauthorized())
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun deleteItemFromCart(cartDomainModel: CartDomainModel): Flow<Resource<ServerGenericResponse>> {
        return flow {
            val cartResponse = try {
                val token = userTokenDataStore.getToken()
                if (token == null) {
                    emit(Resource.NetworkUnauthorized())
                    return@flow
                }
                cartApi.deleteItemFromCart(
                    token = token,
                    cartTransferModel = cartDomainModel.toTransferModel()
                )
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                cartResponse.isSuccessful && cartResponse.body() != null -> {
                    val cart = cartResponse.body()!!
                    emit(Resource.NetworkSuccess(cart))
                }
                !cartResponse.isSuccessful && cartResponse.code() == 401 ->
                    emit(Resource.NetworkUnauthorized())
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun updateItemInCart(cartDomainModel: CartDomainModel): Flow<Resource<ServerGenericResponse>> {
        return flow {
            val cartResponse = try {
                val token = userTokenDataStore.getToken()
                if (token == null) {
                    emit(Resource.NetworkUnauthorized())
                    return@flow
                }
                cartApi.updateItemInCart(
                    token = token,
                    cartTransferModel = cartDomainModel.toTransferModel()
                )
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                cartResponse.isSuccessful && cartResponse.body() != null -> {
                    val cart = cartResponse.body()!!
                    emit(Resource.NetworkSuccess(cart))
                }
                !cartResponse.isSuccessful && cartResponse.code() == 401 ->
                    emit(Resource.NetworkUnauthorized())
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }
}
