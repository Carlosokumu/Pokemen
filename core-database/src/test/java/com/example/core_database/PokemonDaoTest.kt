package com.example.core_database

import com.carlos.database.dao.PokemonDao
import com.carlos.database.mapper.asEntity
import com.carlos.model.Pokemon
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class PokemonDaoTest : LocalDatabase() {


    private lateinit var pokemonDao: PokemonDao

    @Before
    fun init() {
        pokemonDao = db.pokemonDao()
    }


    @Test
    fun insertAndLoadPokemonListTest() = runBlocking {
        val mockDataList = mockPokemonList().asEntity()
        pokemonDao.insertPokemonList(mockDataList)

        val loadFromDB = pokemonDao.getAllPokemonList(page_ = 0)
        assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = listOf(mockPokemon()).asEntity()[0]
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }


    private fun mockPokemon() = Pokemon(
        page = 0,
        name = "bulbasaur",
        url = "https://pokeapi.co/api/v2/pokemon/1/"

    )

    private fun mockPokemonList() = listOf(mockPokemon())
}