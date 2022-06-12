package com.example.data

import com.example.domain.Resource
import javax.inject.Inject

open class BaseRemoteDataSource
@Inject
constructor() {
    inline fun <T> apiCall(api: () -> T): Resource<T> {
        return try {
            Resource.Success(api.invoke())
        } catch (t: Throwable) {
            Resource.Error
        }
    }
}