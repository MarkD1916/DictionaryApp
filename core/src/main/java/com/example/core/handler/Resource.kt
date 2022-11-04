package com.example.core.handler

sealed class Resource<T>(open val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : Resource<T>(data)

    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    open class Loading<T>(data: T? = null) : Resource<T>(data)

    class NoAction<T>(data: T? = null) : Resource<T>(data)

}

sealed class LoadingType<T>(override val data: T? = null) : Resource.Loading<T>() {

    class FullScreenLoading<T>(data: T? = null) : LoadingType<T>(data)

    class ElementLoading<T>(data: T? = null) : LoadingType<T>(data)

    class LoadingFromDB<T>(data: T? = null) : LoadingType<T>(data)

    class FabLoading<T>(data: T? = null) : LoadingType<T>(data)
}