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

            val productResponse = try {
                productApi.getProduct(productId)
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                productResponse.isSuccessful && productResponse.body()?.toDomainModel() != null -> {
                    val product = productResponse.body()!!.toDomainModel()!!
                    productDao.insertProduct(product.toEntityModel())
                    emit(Resource.NetworkSuccess(product))
                }
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getProductSizes(productId: Int): Flow<Resource<AllProductsDomainModel>> {
        return flow {
            val cachedProduct = productDao.getProduct(productId).toDomainModel()
            val cachedSizes = AllProductsDomainModel(
                productDao.getOtherSizesForProduct(productId, cachedProduct.color).map { it.toDomainModel() }
            )
            emit(Resource.Cached(cachedSizes))

            val sizesResponse = try {
                productApi.getProductSizes(productId)
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                sizesResponse.isSuccessful && sizesResponse.body()?.toDomainModel() != null -> {
                    val sizes = sizesResponse.body()!!.toDomainModel()!!
                    productDao.insertProducts(sizes.products.map { it.toEntityModel() })
                    emit(Resource.NetworkSuccess(sizes))
                }
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getProducts(): Flow<Resource<AllProductsDomainModel>> {
        return flow {
            val cachedProducts = productDao.getProducts().map { it.toDomainModel() }
            emit(Resource.Cached(AllProductsDomainModel(cachedProducts)))

            val productsResponse = try {
                productApi.getProducts()
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                productsResponse.isSuccessful && productsResponse.body()?.toDomainModel() != null -> {
                    val products = productsResponse.body()!!.toDomainModel()!!
                    productDao.repopulateCache(products.products.map { it.toEntityModel() })
                    emit(Resource.NetworkSuccess(products))
                }
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getProducts(productsFilterDomainModel: ProductsFilterDomainModel): Flow<Resource<AllProductsDomainModel>> {
        return flow {
            val cachedProducts = productDao.getProductsFiltered(
                categories = productsFilterDomainModel.categories,
                priceFrom = productsFilterDomainModel.priceFrom,
                priceTo = productsFilterDomainModel.priceTo,
                producers = productsFilterDomainModel.producers,
                sizes = productsFilterDomainModel.sizes,
                colors = productsFilterDomainModel.colors
            ).map { it.toDomainModel() }
            emit(Resource.Cached(AllProductsDomainModel(cachedProducts)))

            val productsResponse = try {
                productApi.getProducts(productsFilterDomainModel.toTransferModel())
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                productsResponse.isSuccessful && productsResponse.body()?.toDomainModel() != null -> {
                    val products = productsResponse.body()!!.toDomainModel()!!
                    productDao.insertProducts(products.products.map { it.toEntityModel() })
                    emit(Resource.NetworkSuccess(products))
                }
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getCategories(): Flow<Resource<SingleListDomainModel<String>>> {
        return flow {
            val cachedCategories = productDao.getCategories()
            emit(Resource.Cached(SingleListDomainModel(cachedCategories)))

            val categoriesResponse = try {
                productApi.getCategories()
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                categoriesResponse.isSuccessful && categoriesResponse.body()?.toDomainModel() != null -> {
                    val categories = categoriesResponse.body()!!.toDomainModel()!!
                    emit(Resource.NetworkSuccess(categories))
                }
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getProducers(): Flow<Resource<SingleListDomainModel<String>>> {
        return flow {
            val cachedProducers = productDao.getProducers()
            emit(Resource.Cached(SingleListDomainModel(cachedProducers)))

            val producersResponse = try {
                productApi.getProducers()
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                producersResponse.isSuccessful && producersResponse.body()?.toDomainModel() != null -> {
                    val producers = producersResponse.body()!!.toDomainModel()!!
                    emit(Resource.NetworkSuccess(producers))
                }
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getSizes(): Flow<Resource<SingleListDomainModel<String>>> {
        return flow {
            val cachedSizes = productDao.getSizes()
            emit(Resource.Cached(SingleListDomainModel(cachedSizes)))

            val sizesResponse = try {
                productApi.getSizes()
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                sizesResponse.isSuccessful && sizesResponse.body()?.toDomainModel() != null -> {
                    val sizes = sizesResponse.body()!!.toDomainModel()!!
                    emit(Resource.NetworkSuccess(sizes))
                }
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getColors(): Flow<Resource<SingleListDomainModel<String>>> {
        return flow {
            val cachedColors = productDao.getColors()
            emit(Resource.Cached(SingleListDomainModel(cachedColors)))

            val colorsResponse = try {
                productApi.getColors()
            } catch (exception: Exception) {
                emit(Resource.NetworkError())
                return@flow
            }

            when {
                colorsResponse.isSuccessful && colorsResponse.body()?.toDomainModel() != null -> {
                    val colors = colorsResponse.body()!!.toDomainModel()!!
                    emit(Resource.NetworkSuccess(colors))
                }
                else ->
                    emit(Resource.NetworkError())
            }
        }.flowOn(Dispatchers.IO)
    }
}
