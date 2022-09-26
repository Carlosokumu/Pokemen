package com.carlos.network.network

import com.carlos.network.models.PokemonResponse
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.Dispatcher
import javax.inject.Inject

class ApiClient @Inject constructor(val dispatcher: CoroutineDispatcher,val pokemenService: PokemenService) {

    suspend fun fetchPokemonList(page: Int): PokemonResponse = pokemenService.fetchPokemonList(offset = page)

    suspend fun fetchPokemonInfo(name: String) = safeApiCall(dispatcher){
        return@safeApiCall pokemenService.fetchPokemonInfo(name = name)
    }





}