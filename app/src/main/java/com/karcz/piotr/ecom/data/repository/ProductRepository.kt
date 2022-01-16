package com.karcz.piotr.ecom.data.repository

import com.karcz.piotr.ecom.data.Resource
import com.karcz.piotr.ecom.data.api.ProductApi
import com.karcz.piotr.ecom.data.database.ProductDao
import com.karcz.piotr.ecom.data.domain.AllProductsDomainModel
import com.karcz.piotr.ecom.data.domain.ProductDomainModel
import com.karcz.piotr.ecom.data.domain.ProductsFilterDomainModel
import com.karcz.piotr.ecom.data.domain.SingleListDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productApi: ProductApi,
    private val productDao: ProductDao
) {

    fun getProduct(productId: Int): Flow<Resource<ProductDomainModel>> {
        return flow {
            val cachedProduct = productDao.getProduct(productId).toDomainModel()
            emit(Resource.Cached(cachedProduct))
            try {
                val product = productApi.getProduct(productId).toDomainModel()
                if (product != null) {
                    productDao.insertProduct(product.toEntityModel())
                    emit(Resource.NetworkSuccess(product))
                } else {
                    emit(Resource.NetworkError())
                }
            } catch (exception: Exception) {
                emit(Resource.NetworkError(exception))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getProductSizes(productId: Int): Flow<Resource<AllProductsDomainModel>> {
        return flow {
            val cachedProduct = productDao.getProduct(productId).toDomainModel()
            val cachedSizes = AllProductsDomainModel(
                productDao.getOtherSizesForProduct(productId, cachedProduct.color).map { it.toDomainModel() }
            )
            emit(Resource.Cached(cachedSizes))
            try {
                val sizes = productApi.getProductSizes(productId).toDomainModel()
                if (sizes != null) {
                    productDao.insertProducts(sizes.products.map { it.toEntityModel() })
                    emit(Resource.NetworkSuccess(sizes))
                } else {
                    emit(Resource.NetworkError())
                }
            } catch (exception: Exception) {
                emit(Resource.NetworkError(exception))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getProducts(): Flow<Resource<AllProductsDomainModel>> {
        return flow {
            val cachedProducts = productDao.getProducts().map { it.toDomainModel() }
            emit(Resource.Cached(AllProductsDomainModel(cachedProducts)))
            try {
                val products = productApi.getProducts().toDomainModel()
                if (products != null) {
                    productDao.repopulateCache(products.products.map { it.toEntityModel() })
                    emit(Resource.NetworkSuccess(products))
                } else {
                    emit(Resource.NetworkError())
                }
            } catch (exception: Exception) {
                emit(Resource.NetworkError(exception))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getProducts(productsFilterDomainModel: ProductsFilterDomainModel): Flow<Resource<AllProductsDomainModel>> {
        return flow {
            val cachedProducts = productDao.getProductsFiltered(
                categories = productsFilterDomainModel.categories,
                priceFrom = productsFilterDomainModel.priceFrom,
                priceTo = productsFilterDomainModel.priceTo,
                producers = productsFilterDomainModel.producers,
                sizes = productsFilterDomainModel.sizes,
                colors = productsFilterDomainModel.colors
            )
            emit(Resource.Cached(AllProductsDomainModel(cachedProducts.map { it.toDomainModel() })))
            try {
                val productsFilterTransferModel = productsFilterDomainModel.toTransferModel()
                val products = productApi.getProducts(productsFilterTransferModel).toDomainModel()
                if (products != null) {
                    productDao.insertProducts(cachedProducts)
                    emit(Resource.NetworkSuccess(products))
                } else {
                    emit(Resource.NetworkError())
                }
            } catch (exception: Exception) {
                emit(Resource.NetworkError(exception))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCategories(): Flow<Resource<SingleListDomainModel<String>>> {
        return flow {
            val cachedCategories = productDao.getCategories()
            emit(Resource.Cached(SingleListDomainModel(cachedCategories)))
            try {
                val categories = productApi.getCategories().toDomainModel()
                if (categories != null) {
                    emit(Resource.NetworkSuccess(categories))
                } else {
                    emit(Resource.NetworkError())
                }
            } catch (exception: Exception) {
                emit(Resource.NetworkError(exception))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getProducers(): Flow<Resource<SingleListDomainModel<String>>> {
        return flow {
            val cachedProducers = productDao.getProducers()
            emit(Resource.Cached(SingleListDomainModel(cachedProducers)))
            try {
                val producers = productApi.getProducers().toDomainModel()
                if (producers != null) {
                    emit(Resource.NetworkSuccess(producers))
                } else {
                    emit(Resource.NetworkError())
                }
            } catch (exception: Exception) {
                emit(Resource.NetworkError(exception))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSizes(): Flow<Resource<SingleListDomainModel<String>>> {
        return flow {
            val cachedSizes = productDao.getSizes()
            emit(Resource.Cached(SingleListDomainModel(cachedSizes)))
            try {
                val sizes = productApi.getSizes().toDomainModel()
                if (sizes != null) {
                    emit(Resource.NetworkSuccess(sizes))
                } else {
                    emit(Resource.NetworkError())
                }
            } catch (exception: Exception) {
                emit(Resource.NetworkError(exception))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getColors(): Flow<Resource<SingleListDomainModel<String>>> {
        return flow {
            val cachedColors = productDao.getColors()
            emit(Resource.Cached(SingleListDomainModel(cachedColors)))
            try {
                val colors = productApi.getColors().toDomainModel()
                if (colors != null) {
                    emit(Resource.NetworkSuccess(colors))
                } else {
                    emit(Resource.NetworkError())
                }
            } catch (exception: Exception) {
                emit(Resource.NetworkError(exception))
            }
        }.flowOn(Dispatchers.IO)
    }
}
