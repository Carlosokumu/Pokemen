package com.carlos.pokemen.paging

import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.carlos.network.models.Pokemon
import com.carlos.network.models.PokemonResponse
import com.carlos.network.network.ApiCallResult
import com.carlos.network.network.ApiClient
import retrofit2.HttpException
import java.io.IOException

class PokemonDataSource(private val apiClient: ApiClient): PagingSource<Int, Pokemon>() {
    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
          return  state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val nextPage = params.key ?: 1
            val pokemonList = apiClient.fetchPokemonList(page = nextPage)
            LoadResult.Page(
                data = pokemonList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (pokemonList.results.isEmpty()) null else  1
            )

        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}