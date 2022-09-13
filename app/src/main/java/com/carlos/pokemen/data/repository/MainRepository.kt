package com.carlos.pokemen.data.repository

interface MainRepository {

    suspend fun  fetchPokemonList(page: Int)
}