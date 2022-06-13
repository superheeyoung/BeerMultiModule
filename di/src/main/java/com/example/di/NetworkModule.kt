package com.example.di

import com.example.data.BeerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okhttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/v2/")
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun providesBeerService(retrofit: Retrofit.Builder): BeerApi {
        return retrofit
            .build()
            .create(BeerApi::class.java)
    }
}