package com.carlos.data

import androidx.paging.PagingData
import com.carlos.database.entity.PokemonEntity
import com.carlos.model.Pokemon
import com.carlos.model.PokemonInfo
import com.carlos.network.network.ApiCallResult
import kotlinx.coroutines.flow.Flow

interface MainRepository {


    suspend fun  fetchPokemonList(page: Int): Flow<PagingData<PokemonEntity>>

    suspend fun fetchPokemonInfo(name: String): ApiCallResult<PokemonInfo>
}