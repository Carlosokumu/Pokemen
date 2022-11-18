package com.carlos.data

import com.carlos.model.Pokemon
import com.carlos.model.PokemonInfo
import com.carlos.model.Specie


object MockUtil {
    fun mockPokemonInfo() = PokemonInfo(
        id = 1,
        name = "bulbasaur",
        height = 7,
        weight = 69,
        stats = emptyList(),
        types = emptyList(),
        species = Specie(name = "name", url = "https:url")
    )


    fun mockPokemon() = Pokemon(
        page = 0,
        name = "bulbasaur",
        url = "https://pokeapi.co/api/v2/pokemon/1/"

    )

    fun mockPokemonList() = listOf(mockPokemon())


    fun mockPokemonList20(): List<Pokemon> {
        val pokemons: MutableList<Pokemon> = mutableListOf()
        for (i in 0..20) {
            pokemons.add(mockPokemon())
        }
        return pokemons
    }

}
