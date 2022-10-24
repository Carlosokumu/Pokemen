package com.carlos.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carlos.database.dao.PokemonDao
import com.carlos.database.entity.PokemonEntity


@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = true
)

abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}