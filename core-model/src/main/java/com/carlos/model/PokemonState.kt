package com.carlos.model




sealed class PokemonState {
    object  Loading: PokemonState()

    object  Result: PokemonState()

    data class  Error(val message: String): PokemonState()
}
