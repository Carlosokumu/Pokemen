package com.carlos.network.network

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

open class ApiClient @Inject constructor(val dispatcher: CoroutineDispatcher,val pokemonService: PokemonService) {

    suspend fun fetchPokemonList(page: Int) = pokemonService.fetchPokemonList(offset = page)

    suspend fun fetchPokemonInfo(name: String) = safeApiCall(dispatcher){
        return@safeApiCall pokemonService.fetchPokemonInfo(name = name)
    }





}