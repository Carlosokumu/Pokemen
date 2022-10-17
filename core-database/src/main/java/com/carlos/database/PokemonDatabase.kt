package com.carlos.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carlos.database.entity.PokemonEntity


@Database(
    entities = [PokemonEntity::class],
    version = 2,
    exportSchema = true
)

abstract class PokemonDatabase : RoomDatabase() {


}