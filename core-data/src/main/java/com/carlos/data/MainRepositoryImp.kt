package com.carlos.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.carlos.data.paging.PokemonPagingDatasource
import com.carlos.database.PokemonDatabase
import com.carlos.database.entity.PokemonEntity
import com.carlos.database.mapper.asDomain
import com.carlos.database.mapper.asEntity
import com.carlos.model.PokemonInfo
import com.carlos.network.network.ApiCallResult
import com.carlos.network.network.ApiClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(private val apiClient: ApiClient,private val pokemonDatabase: PokemonDatabase) : MainRepository {


    @OptIn(ExperimentalPagingApi::class)
    override suspend fun fetchPokemonList(): Flow<PagingData<PokemonEntity>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                PokemonPagingDatasource(apiClient = apiClient,pokemonDatabase.pokemonDao())
            }
        ).flow
    }

    override suspend fun fetchPokemonInfo(name: String): PokemonInfo? {
        val pokemonInfo = pokemonDatabase.pokemonInfoDao().getPokemonInfo(name_ = name)
        if ( pokemonInfo == null){
            return when(val result = apiClient.fetchPokemonInfo(name)){
                is ApiCallResult.ServerError -> {
                    null
                }
                is ApiCallResult.ApiCallError -> {
                    null
                }
                is ApiCallResult.Success -> {
                    pokemonDatabase.pokemonInfoDao().insertPokemonInfo(result.data.asEntity())
                    result.data
                }
            }
        }
        else {
            return  pokemonInfo.asDomain()
        }
    }

    override suspend fun fetchPokemonListTwo(): Flow<PagingData<PokemonEntity>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            pagingSourceFactory = {
                PokemonPagingDatasource(apiClient = apiClient,pokemonDatabase.pokemonDao())
            }
        ).flow
    }


}