package com.karcz.piotr.ecom.data.repository

import com.karcz.piotr.ecom.data.api.CartApi
import com.karcz.piotr.ecom.data.database.UserTokenDataStore
import com.karcz.piotr.ecom.data.domain.AllCartsDomainModel
import com.karcz.piotr.ecom.data.domain.CartDomainModel
import com.karcz.piotr.ecom.data.transfer.ServerGenericResponse
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartApi: CartApi,
    private val userTokenDataStore: UserTokenDataStore
) {

    suspend fun getCartProducts(): AllCartsDomainModel? {
        return cartApi
            .getCartProducts(token = userTokenDataStore.getToken())
            .toDomainModel()
    }

    suspend fun addProductsToCart(cartDomainModel: CartDomainModel): ServerGenericResponse {
        val cartTransferModel = cartDomainModel.toTransferModel()
        return cartApi.addProductsToCart(
            token = userTokenDataStore.getToken(),
            cartTransferModel = cartTransferModel
        )
    }

    suspend fun deleteItemFromCart(cartDomainModel: CartDomainModel): ServerGenericResponse {
        val cartTransferModel = cartDomainModel.toTransferModel()
        return cartApi.deleteItemFromCart(
            token = userTokenDataStore.getToken(),
            cartTransferModel = cartTransferModel
        )
    }

    suspend fun updateItemInCart(cartDomainModel: CartDomainModel): ServerGenericResponse {
        val cartTransferModel = cartDomainModel.toTransferModel()
        return cartApi.updateItemInCart(
            token = userTokenDataStore.getToken(),
            cartTransferModel = cartTransferModel
        )
    }
}
