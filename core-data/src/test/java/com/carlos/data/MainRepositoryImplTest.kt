package com.carlos.data

import androidx.paging.PagingSource
import com.carlos.data.MockUtil.mockPokemonList
import com.carlos.data.paging.PokemonPagingDatasource
import com.carlos.data.repository.MainRepositoryImp
import com.carlos.database.dao.PokemonDao
import com.carlos.database.mapper.asEntity
import com.carlos.network.models.PokemonResponse
import com.carlos.network.network.ApiClient
import com.carlos.network.network.PokemenService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainRepositoryImplTest {

    private val pokemonDao: PokemonDao = mock()
    private var apiClient: ApiClient = mock()
    private lateinit var mainRepositoryImp: MainRepositoryImp
    private lateinit var pagingDatasource: PokemonPagingDatasource


    private val service: PokemenService = mock()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()


    @Before
    fun setUp() {

        mainRepositoryImp = MainRepositoryImp(apiClient, pokemonDao)
        pagingDatasource = PokemonPagingDatasource(apiClient = apiClient, pokemonDao = pokemonDao)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testPagingDatasource() = runTest {

        val mockData =
            PokemonResponse(page = "0", results = mockPokemonList())
        whenever(pokemonDao.getAllPokemonList(page_ = 0)).thenReturn(mockData.results.asEntity())
        val expected = PagingSource.LoadResult.Page(
            data = mockData.results.asEntity(),
            prevKey = null,
            nextKey = 1
        )
        val actual = pagingDatasource.load(
            PagingSource.LoadParams.Append(
                key = 0,
                loadSize = LOAD_SIZE,
                placeholdersEnabled = false
            )
        )

        pokemonDao.getAllPokemonList(page_ = 0)
        assertEquals(expected, actual)

    }


    companion object {
        const val LOAD_SIZE = 20
    }


}