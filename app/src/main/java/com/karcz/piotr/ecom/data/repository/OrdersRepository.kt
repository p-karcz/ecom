package com.karcz.piotr.ecom.data.repository

import com.karcz.piotr.ecom.data.Resource
import com.karcz.piotr.ecom.data.api.OrdersApi
import com.karcz.piotr.ecom.data.database.UserTokenDataStore
import com.karcz.piotr.ecom.data.domain.AllOrderDetailsDomainModel
import com.karcz.piotr.ecom.data.domain.AllOrderDetailsWithProductsDomainModel
import com.karcz.piotr.ecom.data.domain.AllOrdersDomainModel
import com.karcz.piotr.ecom.data.transfer.ServerGenericResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val ordersApi: OrdersApi,
    private val userTokenDataStore: UserTokenDataStore
) {

    fun getOrders(): Flow<Resource<AllOrdersDomainModel>> {
        return flow {
            val ordersResponse = try {
                val token = userTokenDataStore.getToken()
                if (token == null) {
                    emit(Resource.NetworkUnauthenticated())
                    return@flow
                }
                ordersApi.getOrders(token = token)
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                ordersResponse.isSuccessful && ordersResponse.body()?.toDomainModel() != null -> {
                    val customer = ordersResponse.body()!!.toDomainModel()!!
                    emit(Resource.NetworkSuccess(customer))
                }
                !ordersResponse.isSuccessful && ordersResponse.code() == 401 ->
                    emit(Resource.NetworkUnauthenticated())
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun addOrder(allOrderDetailsDomainModel: AllOrderDetailsDomainModel): Flow<Resource<ServerGenericResponse>> {
        return flow {
            val orderResponse = try {
                val token = userTokenDataStore.getToken()
                if (token == null) {
                    emit(Resource.NetworkUnauthenticated())
                    return@flow
                }
                ordersApi.addOrder(
                    token = token,
                    allOrderDetailsTransferModel = allOrderDetailsDomainModel.toTransferModel()
                )
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                orderResponse.isSuccessful && orderResponse.body() != null -> {
                    val order = orderResponse.body()!!
                    emit(Resource.NetworkSuccess(order))
                }
                !orderResponse.isSuccessful && orderResponse.code() == 401 ->
                    emit(Resource.NetworkUnauthenticated())
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getOrder(orderId: Int): Flow<Resource<AllOrderDetailsWithProductsDomainModel>> {
        return flow {
            val orderResponse = try {
                val token = userTokenDataStore.getToken()
                if (token == null) {
                    emit(Resource.NetworkUnauthenticated())
                    return@flow
                }
                ordersApi.getOrder(
                    token = token,
                    orderId = orderId
                )
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                orderResponse.isSuccessful && orderResponse.body()?.toDomainModel() != null -> {
                    val customer = orderResponse.body()!!.toDomainModel()!!
                    emit(Resource.NetworkSuccess(customer))
                }
                !orderResponse.isSuccessful && orderResponse.code() == 401 ->
                    emit(Resource.NetworkUnauthenticated())
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }
}
