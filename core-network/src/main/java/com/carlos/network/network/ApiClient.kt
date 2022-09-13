package com.carlos.network.network

import javax.inject.Inject

class ApiClient @Inject constructor(private val pokemenService: PokemenService) {

    suspend fun fetchPokemonList(){

    }
}