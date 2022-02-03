package com.karcz.piotr.ecom.data

sealed class Resource<T>() {
    class NetworkSuccess<T>(val data: T) : Resource<T>()
    class NetworkError<T>(val exception: Exception? = null) : Resource<T>()
    class NetworkUnauthorized<T> : Resource<T>()
    class Cached<T>(val data: T) : Resource<T>()
}
