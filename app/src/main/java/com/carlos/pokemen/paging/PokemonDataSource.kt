//package com.carlos.pokemen.paging
//
//import android.util.Log
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.carlos.model.Pokemon
//import com.carlos.network.network.ApiClient
//import retrofit2.HttpException
//import java.io.IOException
//
//class PokemonDataSource(private val apiClient: ApiClient): PagingSource<Int, com.carlos.model.Pokemon>() {
//    override fun getRefreshKey(state: PagingState<Int, com.carlos.model.Pokemon>): Int? {
//          return  state.anchorPosition
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.carlos.model.Pokemon> {
//        return try {
//            val nextPage = params.key ?: POKEMON_STARTING_INDEX
//            Log.d("PAGENUMBER",nextPage.toString())
//            val pokemonList = apiClient.fetchPokemonList(page = nextPage * 20)
//            LoadResult.Page(
//                data = pokemonList.results,
//                prevKey = if (nextPage == 0) null else nextPage - 1,
//                nextKey = if (pokemonList.results.isEmpty()) null else  nextPage + 1
//            )
//
//        } catch (e: IOException) {
//            return LoadResult.Error(e)
//        } catch (e: HttpException) {
//            return LoadResult.Error(e)
//        }
//    }
//
//    companion object {
//       const val POKEMON_STARTING_INDEX = 0
//    }
//}