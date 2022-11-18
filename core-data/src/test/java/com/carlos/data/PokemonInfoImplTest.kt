package com.carlos.data

import com.carlos.data.MockUtil.mockPokemonInfo
import com.carlos.data.repository.PokemonInfoRepositoryImp
import com.carlos.database.dao.PokemonInfoDao
import com.carlos.database.mapper.asEntity
import com.carlos.network.network.ApiClient
import com.carlos.network.network.PokemenService
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonInfoImplTest {

    private val pokemonInfoDao: PokemonInfoDao = mock()
    private lateinit var apiClient: ApiClient
    private lateinit var pokemonInfoRepositoryImp: PokemonInfoRepositoryImp


    private val service: PokemenService = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        apiClient = ApiClient(coroutinesRule.testDispatcher, service)
        pokemonInfoRepositoryImp = PokemonInfoRepositoryImp(apiClient, pokemonInfoDao)
    }

    @Test
    fun testFetchPokemonInfoNetwork() = runTest {

        val mockData = mockPokemonInfo()
        whenever(pokemonInfoDao.getPokemonInfo(name_ = "bulbasaur")).thenReturn(null)
        whenever(service.fetchPokemonInfo(name = "bulbasaur")).thenReturn(mockData)
        val expectedItem = pokemonInfoRepositoryImp.fetchPokemonInfo(name = "bulbasaur")
        assertEquals(expectedItem?.name, mockData.name)
        assertEquals(expectedItem?.id, mockData.id)
        verify(pokemonInfoDao, atLeastOnce()).getPokemonInfo(name_ = "bulbasaur")
        verify(pokemonInfoDao, atLeastOnce()).insertPokemonInfo(pokemonInfo = mockData.asEntity())
        verify(service, atLeastOnce()).fetchPokemonInfo(name = "bulbasaur")


    }


    @Test
    fun testFetchPokemonInfoFromDb() = runTest {
        val mockData = mockPokemonInfo()
        whenever(pokemonInfoDao.getPokemonInfo(name_ = "bulbasaur")).thenReturn(mockData.asEntity())
        val expectedItem = pokemonInfoRepositoryImp.fetchPokemonInfo(name = "bulbasaur")
        assertEquals(expectedItem?.name, mockData.name)
        assertEquals(expectedItem?.id, mockData.id)
        verify(pokemonInfoDao, atLeastOnce()).getPokemonInfo(name_ = "bulbasaur")


    }


}