package com.carlos.data.repository

import androidx.paging.PagingData
import com.carlos.database.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface MainRepository {


    suspend fun fetchPokemonList(): Flow<PagingData<PokemonEntity>>
}