package com.carlos.network.network

import com.carlos.network.models.PokemonResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ApiClient @Inject constructor(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO, val pokemenService: PokemenService) {

    suspend fun fetchPokemonList(page: Int): PokemonResponse = pokemenService.fetchPokemonList(offset = page)


    companion object {
        const val LIMIT = 20
    }




}