package com.example.data

import javax.inject.Inject

class BeerRemoteSource
@Inject
constructor(private val beerApi: BeerApi) : BaseRemoteDataSource() {
    suspend fun getBeerList() = apiCall {
        beerApi.getBeerList()
    }
}