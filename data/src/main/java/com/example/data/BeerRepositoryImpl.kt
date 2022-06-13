package com.example.data

import com.example.domain.Beer
import com.example.domain.BeerRepository
import com.example.domain.Resource
import com.example.domain.Resource.Loading.mapToEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerRepositoryImpl
@Inject
constructor(
    private val beerEntityMapper: BeerEntityMapper,
    private val beerRemoteSource: BeerRemoteSource
) : BeerRepository {
    override suspend fun getBeerList(): Resource<List<Beer>> {
        return beerRemoteSource.getBeerList().mapToEntity { beerEntityMapper.transform(it) }
    }
}
