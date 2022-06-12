package com.example.domain

interface BeerRepository {
    suspend fun getBeerList() : Resource<List<Beer>>
}