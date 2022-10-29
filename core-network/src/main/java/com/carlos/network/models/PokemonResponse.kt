package com.carlos.network.models

data class PokemonResponse(
    val page: String,
    val results: List<com.carlos.model.Pokemon>
)
