package com.carlos.database.dao

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carlos.database.entity.PokemonEntity

@OptIn(ExperimentalPagingApi::class)
@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<PokemonEntity>)

    @Query("SELECT * FROM PokemonEntity WHERE page LIKE :page")
    fun getPokemonList(page: Int): PagingSource<Int,PokemonEntity>

//    @Query("SELECT * FROM PokemonEntity WHERE page <= :page_")
//    suspend fun getAllPokemonList(page_: Int): List<PokemonEntity>
}

