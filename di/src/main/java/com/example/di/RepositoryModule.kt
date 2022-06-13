package com.example.di

import com.example.data.BeerEntityMapper
import com.example.data.BeerRemoteSource
import com.example.data.BeerRepositoryImpl
import com.example.domain.BeerRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Provides
    fun bindBeerRepository(beerEntityMapper: BeerEntityMapper,
                           beerRemoteSource: BeerRemoteSource
    ) : BeerRepository
}