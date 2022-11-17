package com.carlos.data.repository

import com.carlos.model.PokemonInfo

interface PokemonInfoRepository {


    suspend fun fetchPokemonInfo(name: String): PokemonInfo?
}