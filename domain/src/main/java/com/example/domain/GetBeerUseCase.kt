package com.example.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetBeerUseCase
@Inject
constructor(private val beerRepository: BeerRepository)
{
    operator fun invoke() : Flow<Resource<List<Beer>>> = flow {
        emit(Resource.Loading)
        val item = beerRepository.getBeerList()
        emit(item)
    }
        .catch { emit(Resource.Error)}
        .flowOn(Dispatchers.IO)
}