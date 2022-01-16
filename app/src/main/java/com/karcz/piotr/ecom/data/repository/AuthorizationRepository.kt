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
            try {
                val token = authorizationApi.register(customerRegistrationDomainModel.toTransferModel()).toDomainModel()
                if (token != null) {
                    userTokenDataStore.saveToken(token.token)
                    emit(Resource.NetworkSuccess(token))
                }
            } catch (exception: Exception) {
                emit(Resource.NetworkError(exception))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun login(customerLoginDomainModel: CustomerLoginDomainModel): Flow<Resource<TokenDomainModel>> {
        return flow {
            emit(Resource.NetworkLoading())
            try {
                val token = authorizationApi.login(customerLoginDomainModel.toTransferModel()).toDomainModel()
                if (token != null) {
                    userTokenDataStore.saveToken(token.token)
                    emit(Resource.NetworkSuccess(token))
                }
            } catch (exception: Exception) {
                emit(Resource.NetworkError(exception))
            }
        }
    }
}
