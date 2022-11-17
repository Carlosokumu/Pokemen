package com.carlos.data.repository

import androidx.paging.PagingData
import com.carlos.database.entity.PokemonEntity
import com.carlos.model.Pokemon
import com.carlos.model.PokemonInfo
import com.carlos.network.network.ApiCallResult
import kotlinx.coroutines.flow.Flow

interface MainRepository {





    suspend fun  fetchPokemonList(): Flow<PagingData<PokemonEntity>>
}