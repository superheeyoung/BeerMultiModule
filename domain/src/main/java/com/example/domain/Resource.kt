package com.example.domain

sealed class Resource<out T> {

    data class Success<out T>(
        val value: T
    ) : Resource<T>()

    object Loading : Resource<Nothing>()
    object Error : Resource<Nothing>()


    inline fun <reified T, reified V> Resource<T>.mapToEntity(callback : (value : T) -> V) : Resource<V> {
        return when(this) {
            is Resource.Success -> Resource.Success(callback(value))
            is Resource.Error -> Resource.Error
            is Resource.Loading -> Resource.Loading
        }
    }
}