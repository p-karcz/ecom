package com.karcz.piotr.ecom.data.repository

import com.karcz.piotr.ecom.data.api.OrdersApi
import com.karcz.piotr.ecom.data.database.UserTokenDataStore
import com.karcz.piotr.ecom.data.domain.AllOrderDetailsDomainModel
import com.karcz.piotr.ecom.data.domain.AllOrderDetailsWithProductsDomainModel
import com.karcz.piotr.ecom.data.domain.AllOrdersDomainModel
import com.karcz.piotr.ecom.data.transfer.AllOrderDetailsWithProductsTransferModel
import com.karcz.piotr.ecom.data.transfer.AllOrdersTransferModel
import com.karcz.piotr.ecom.data.transfer.ServerGenericResponse
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val ordersApi: OrdersApi,
    private val userTokenDataStore: UserTokenDataStore
) {

    suspend fun getOrders(): AllOrdersDomainModel? {
        return ordersApi
            .getOrders(token = userTokenDataStore.getToken())
            .toDomain()
    }

    suspend fun addOrder(allOrderDetailsDomainModel: AllOrderDetailsDomainModel): ServerGenericResponse {
        val allOrderDetailsTransferModel = allOrderDetailsDomainModel.toTransferModel()
        return ordersApi.addOrder(
            token = userTokenDataStore.getToken(),
            allOrderDetailsTransferModel = allOrderDetailsTransferModel
        )
    }

    suspend fun getOrder(orderId: Int): AllOrderDetailsWithProductsDomainModel? {
        return ordersApi.getOrder(
            token = userTokenDataStore.getToken(),
            orderId = orderId
        ).toDomainModel()
    }
}
