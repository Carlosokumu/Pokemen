package com.carlos.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.carlos.data.paging.PokemonPagingDatasource
import com.carlos.data.repository.MainRepository
import com.carlos.database.PokemonDatabase
import com.carlos.database.dao.PokemonDao
import com.carlos.database.entity.PokemonEntity
import com.carlos.database.mapper.asDomain
import com.carlos.database.mapper.asEntity
import com.carlos.model.PokemonInfo
import com.carlos.network.network.ApiCallResult
import com.carlos.network.network.ApiClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(private val apiClient: ApiClient,private val pokemonDao: PokemonDao) :
    MainRepository {


    override suspend fun fetchPokemonList(): Flow<PagingData<PokemonEntity>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                PokemonPagingDatasource(apiClient = apiClient, pokemonDao = pokemonDao)
            }
        ).flow
    }


}