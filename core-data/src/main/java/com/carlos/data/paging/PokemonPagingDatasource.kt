package com.carlos.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.carlos.database.dao.PokemonDao
import com.carlos.database.entity.PokemonEntity
import com.carlos.database.mapper.asEntity
import com.carlos.network.network.ApiClient
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingDatasource(
    private val apiClient: ApiClient,
    private val pokemonDao: PokemonDao
) : PagingSource<Int, PokemonEntity>() {


    override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? {
        return state.anchorPosition
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {

        try {
            val nextKey = params.key ?: POKEMON_STARTING_INDEX
            val cachedList = pokemonDao.getAllPokemonList(page_ = nextKey)

            if (cachedList.isNullOrEmpty()) {
                val apiData = apiClient.fetchPokemonList(nextKey * OFFSET)
                if (apiData.results.isNotEmpty()) {
                    apiData.results.map {
                        it.page = nextKey
                    }
                    pokemonDao.insertPokemonList(apiData.results.asEntity())
                    return LoadResult.Page(
                        data = pokemonDao.getAllPokemonList(nextKey),
                        prevKey = if (nextKey == POKEMON_STARTING_INDEX) null else nextKey - 1,
                        nextKey = if (apiData.results.isEmpty()) null else nextKey + 1
                    )
                }
            }
            return LoadResult.Page(
                data = cachedList,
                prevKey = if (nextKey == POKEMON_STARTING_INDEX) null else nextKey - 1,
                nextKey = nextKey + 1
            )


        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }


    companion object {
        const val POKEMON_STARTING_INDEX = 0
        const val OFFSET = 20
    }
}