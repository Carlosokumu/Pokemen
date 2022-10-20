package com.carlos.data

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.paging.*
import com.carlos.data.paging.NetworkMediator
import com.carlos.database.PokemonDatabase
import com.carlos.database.dao.PokemonDao
import com.carlos.database.entity.PokemonEntity
import com.carlos.model.Pokemon
import com.carlos.model.PokemonInfo
import com.carlos.network.network.ApiCallResult
import com.carlos.network.network.ApiClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(private val apiClient: ApiClient,private val pokemonDatabase: PokemonDatabase) : MainRepository {


    @OptIn(ExperimentalPagingApi::class)
    override suspend fun fetchPokemonList(page: Int): Flow<PagingData<PokemonEntity>> {
        val result =  pokemonDatabase.pokemonDao().getPokemonList(page = page)
        Log.d("RESULTSIZE",page.toString())
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 20),
            remoteMediator = NetworkMediator(apiClient = apiClient, pokemonDatabase = pokemonDatabase),
            pagingSourceFactory = {
               result
            }
        ).flow
    }

    override suspend fun fetchPokemonInfo(name: String): ApiCallResult<PokemonInfo> {
        TODO("Not yet implemented")
    }


}