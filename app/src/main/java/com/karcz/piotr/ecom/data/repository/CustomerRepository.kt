package com.karcz.piotr.ecom.data.repository

import com.karcz.piotr.ecom.data.api.CustomerApi
import com.karcz.piotr.ecom.data.database.UserTokenDataStore
import com.karcz.piotr.ecom.data.domain.AddressDomainModel
import com.karcz.piotr.ecom.data.domain.CustomerDomainModel
import com.karcz.piotr.ecom.data.transfer.AddressTransferModel
import com.karcz.piotr.ecom.data.transfer.CustomerTransferModel
import com.karcz.piotr.ecom.data.transfer.ServerGenericResponse
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val customerApi: CustomerApi,
    private val userTokenDataStore: UserTokenDataStore
) {

    suspend fun getCustomer(): CustomerDomainModel? {
        return customerApi
            .getCustomer(token = userTokenDataStore.getToken())
            .toDomainModel()
    }

    suspend fun updateCustomer(customerDomainModel: CustomerDomainModel): ServerGenericResponse {
        val customerTransferModel = customerDomainModel.toTransferModel()
        return customerApi.updateCustomer(
            token = userTokenDataStore.getToken(),
            customerTransferModel = customerTransferModel
        )
    }

    suspend fun removeAccount(customerDomainModel: CustomerDomainModel): ServerGenericResponse {
        val customerTransferModel = customerDomainModel.toTransferModel()
        return customerApi.removeAccount(
            token = userTokenDataStore.getToken(),
            customerTransferModel = customerTransferModel
        )
    }

    suspend fun getAddress(): AddressTransferModel {
        return customerApi.getAddress(token = userTokenDataStore.getToken())
    }

    suspend fun updateAddress(addressDomainModel: AddressDomainModel): ServerGenericResponse {
        val addressTransferModel = addressDomainModel.toTransferModel()
        return customerApi.updateAddress(
            token = userTokenDataStore.getToken(),
            addressTransferModel = addressTransferModel
        )
    }
}
