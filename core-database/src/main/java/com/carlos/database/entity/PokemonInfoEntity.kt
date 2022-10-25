package com.carlos.database.entity

import com.carlos.model.Specie
import com.carlos.model.Stats
import com.carlos.model.TypeResponse

data class PokemonInfoEntity(
    val name: String,
    val  id: Int,
    val hp: Int,
    val attack: Int,
    val defence: Int,
    val speed: Int,
    val exp: Int,
    val height: Int,
    val species: Specie,
    val weight: Int,
    val stats: List<Stats>,
    val types: List<TypeResponse>
)
