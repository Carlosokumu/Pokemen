package com.carlos.network.models




sealed class PokemonState {
    object  Loading: PokemonState()

    data class  Result(val data: PokemonInfo): PokemonState()

    data class  Error(val message: String): PokemonState()
}
