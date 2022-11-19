package com.carlos.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carlos.database.dao.PokemonDao
import com.carlos.database.dao.PokemonInfoDao
import com.carlos.database.entity.PokemonEntity
import com.carlos.database.entity.PokemonInfoEntity


@Database(
    entities = [PokemonEntity::class,PokemonInfoEntity::class],
    version = 3,
    exportSchema = true
)

abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonInfoDao(): PokemonInfoDao
}