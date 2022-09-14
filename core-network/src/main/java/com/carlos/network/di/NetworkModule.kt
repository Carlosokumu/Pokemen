package com.carlos.network.di

import com.carlos.network.network.ApiClient
import com.carlos.network.network.PokemenService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .serializeNulls()
            .create()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://pokeapi.co/api/v2/")
            .build()
    }

    @Provides
    @Singleton
    fun providePokemenService(retrofit: Retrofit): PokemenService {
        return retrofit.create(PokemenService::class.java)
    }
    @Provides
    @Singleton
    fun provideApiClient(pokemenService: PokemenService): ApiClient {
        return ApiClient(pokemenService = pokemenService)
    }
}