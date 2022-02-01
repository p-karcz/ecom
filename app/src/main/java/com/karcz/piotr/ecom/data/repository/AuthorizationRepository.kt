package com.karcz.piotr.ecom.data.repository

import com.karcz.piotr.ecom.data.Resource
import com.karcz.piotr.ecom.data.api.AuthorizationApi
import com.karcz.piotr.ecom.data.database.UserTokenDataStore
import com.karcz.piotr.ecom.data.domain.CustomerLoginDomainModel
import com.karcz.piotr.ecom.data.domain.CustomerRegistrationDomainModel
import com.karcz.piotr.ecom.data.domain.TokenDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthorizationRepository @Inject constructor(
    private val authorizationApi: AuthorizationApi,
    private val userTokenDataStore: UserTokenDataStore
) {

    fun register(customerRegistrationDomainModel: CustomerRegistrationDomainModel): Flow<Resource<TokenDomainModel>> {
        return flow {
            emit(Resource.NetworkLoading())

            val tokenResponse = try {
                authorizationApi.register(customerRegistrationDomainModel.toTransferModel())
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                tokenResponse.isSuccessful && tokenResponse.body()?.toDomainModel() != null -> {
                    val token = tokenResponse.body()!!.toDomainModel()!!
                    userTokenDataStore.saveToken(token.token)
                    emit(Resource.NetworkSuccess(token))
                }
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun login(customerLoginDomainModel: CustomerLoginDomainModel): Flow<Resource<TokenDomainModel>> {
        return flow {
            emit(Resource.NetworkLoading())

            val tokenResponse = try {
                authorizationApi.login(customerLoginDomainModel.toTransferModel())
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                tokenResponse.isSuccessful && tokenResponse.body()?.toDomainModel() != null -> {
                    val token = tokenResponse.body()!!.toDomainModel()!!
                    userTokenDataStore.saveToken(token.token)
                    emit(Resource.NetworkSuccess(token))
                }
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }
}
