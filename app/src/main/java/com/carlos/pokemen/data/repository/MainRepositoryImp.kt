package com.carlos.pokemen.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.carlos.network.models.Pokemon
import com.carlos.network.network.ApiClient
import com.carlos.network.network.safeApiCall
import com.carlos.pokemen.paging.PokemonDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(private val apiClient: ApiClient): MainRepository {

    override suspend fun fetchPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                PokemonDataSource(apiClient = apiClient)
            }
        ).flow
    }





}