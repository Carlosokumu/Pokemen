package com.carlos.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.carlos.database.converters.SpecieConvertor
import com.carlos.database.converters.StatConvertor
import com.carlos.database.converters.TypeResponseConvertor
import com.carlos.model.Specie
import com.carlos.model.Stats
import com.carlos.model.TypeResponse


@Entity
@TypeConverters(value = [TypeResponseConvertor::class,StatConvertor::class,SpecieConvertor::class])
data class PokemonInfoEntity(
    @PrimaryKey
    val  id: Int,
    val name: String,
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
