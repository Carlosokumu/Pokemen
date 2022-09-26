package com.carlos.pokemen.data.repository

import androidx.paging.PagingData
import com.carlos.network.models.Pokemon
import com.carlos.network.models.PokemonInfo
import com.carlos.network.models.PokemonResponse
import com.carlos.network.network.ApiCallResult
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun  fetchPokemonList(): Flow<PagingData<Pokemon>>

    suspend fun fetchPokemonInfo(name: String): ApiCallResult<PokemonInfo>
}