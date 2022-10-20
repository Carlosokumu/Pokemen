package com.carlos.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carlos.database.dao.PokemonDao
import com.carlos.database.dao.RemotePageDao
import com.carlos.database.entity.PokemonEntity
import com.carlos.database.entity.RemoteKey


@Database(
    entities = [PokemonEntity::class,RemoteKey::class],
    version = 1,
    exportSchema = true
)

abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun remoteKeyDao(): RemotePageDao

}