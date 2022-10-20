package com.carlos.network.network

import com.carlos.model.PokemonInfo
import com.carlos.network.models.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  PokemenService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int
    ): PokemonResponse


    @GET("pokemon/{name}")
    suspend fun  fetchPokemonInfo(@Path("name") name: String): PokemonInfo
}