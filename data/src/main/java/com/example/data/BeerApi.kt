package com.example.data

import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {
    @GET("beers")
    suspend fun getBeerList(
    ): List<BeerEntity>
}