package com.karcz.piotr.ecom.data.repository

import com.karcz.piotr.ecom.data.Resource
import com.karcz.piotr.ecom.data.api.CustomerApi
import com.karcz.piotr.ecom.data.database.UserTokenDataStore
import com.karcz.piotr.ecom.data.domain.AddressDomainModel
import com.karcz.piotr.ecom.data.domain.CustomerDomainModel
import com.karcz.piotr.ecom.data.transfer.ServerGenericResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val customerApi: CustomerApi,
    private val userTokenDataStore: UserTokenDataStore
) {

    fun getCustomer(): Flow<Resource<CustomerDomainModel>> {
        return flow {
            val customerResponse = try {
                val token = userTokenDataStore.getToken()
                if (token == null) {
                    emit(Resource.NetworkUnauthenticated())
                    return@flow
                }
                customerApi.getCustomer(token)
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                customerResponse.isSuccessful && customerResponse.body()?.toDomainModel() != null -> {
                    val customer = customerResponse.body()!!.toDomainModel()!!
                    emit(Resource.NetworkSuccess(customer))
                }
                !customerResponse.isSuccessful && customerResponse.code() == 401 ->
                    emit(Resource.NetworkUnauthenticated())
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun updateCustomer(customerDomainModel: CustomerDomainModel): Flow<Resource<ServerGenericResponse>> {
        return flow {
            val customerResponse = try {
                val token = userTokenDataStore.getToken()
                if (token == null) {
                    emit(Resource.NetworkUnauthenticated())
                    return@flow
                }
                customerApi.updateCustomer(
                    token = token,
                    customerTransferModel = customerDomainModel.toTransferModel()
                )
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                customerResponse.isSuccessful && customerResponse.body() != null -> {
                    val customer = customerResponse.body()!!
                    emit(Resource.NetworkSuccess(customer))
                }
                !customerResponse.isSuccessful && customerResponse.code() == 401 ->
                    emit(Resource.NetworkUnauthenticated())
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun removeAccount(customerDomainModel: CustomerDomainModel): Flow<Resource<ServerGenericResponse>> {
        return flow {
            val customerResponse = try {
                val token = userTokenDataStore.getToken()
                if (token == null) {
                    emit(Resource.NetworkUnauthenticated())
                    return@flow
                }
                customerApi.removeAccount(
                    token = token,
                    customerTransferModel = customerDomainModel.toTransferModel()
                )
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                customerResponse.isSuccessful && customerResponse.body() != null -> {
                    val customer = customerResponse.body()!!
                    emit(Resource.NetworkSuccess(customer))
                }
                !customerResponse.isSuccessful && customerResponse.code() == 401 ->
                    emit(Resource.NetworkUnauthenticated())
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAddress(): Flow<Resource<AddressDomainModel>> {
        return flow {
            val addressResponse = try {
                val token = userTokenDataStore.getToken()
                if (token == null) {
                    emit(Resource.NetworkUnauthenticated())
                    return@flow
                }
                customerApi.getAddress(token = token)
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                addressResponse.isSuccessful && addressResponse.body()?.toDomainModel() != null -> {
                    val address = addressResponse.body()!!.toDomainModel()!!
                    emit(Resource.NetworkSuccess(address))
                }
                !addressResponse.isSuccessful && addressResponse.code() == 401 ->
                    emit(Resource.NetworkUnauthenticated())
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun updateAddress(addressDomainModel: AddressDomainModel): Flow<Resource<ServerGenericResponse>> {
        return flow {
            val addressResponse = try {
                val token = userTokenDataStore.getToken()
                if (token == null) {
                    emit(Resource.NetworkUnauthenticated())
                    return@flow
                }
                customerApi.updateAddress(
                    token = token,
                    addressTransferModel = addressDomainModel.toTransferModel()
                )
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                addressResponse.isSuccessful && addressResponse.body() != null -> {
                    val customer = addressResponse.body()!!
                    emit(Resource.NetworkSuccess(customer))
                }
                !addressResponse.isSuccessful && addressResponse.code() == 401 ->
                    emit(Resource.NetworkUnauthenticated())
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }
}
