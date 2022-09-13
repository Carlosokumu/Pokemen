package com.carlos.network.network

import com.carlos.network.models.PokemonResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ApiClient @Inject constructor(private val ioDispatcher: CoroutineDispatcher,val pokemenService: PokemenService) {

    suspend fun fetchPokemonList(page: Int): ApiCallResult<PokemonResponse> = safeApiCall(ioDispatcher) {
        return@safeApiCall pokemenService.fetchPokemonList(limit = LIMIT, offset = page)
    }


    companion object {
        const val LIMIT = 20
    }


}