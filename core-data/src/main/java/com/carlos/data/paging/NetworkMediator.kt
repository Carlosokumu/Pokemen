package com.carlos.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.carlos.database.PokemonDatabase
import com.carlos.database.dao.PokemonDao
import com.carlos.database.entity.PokemonEntity
import com.carlos.database.entity.RemoteKey
import com.carlos.database.mapper.asEntity
import com.carlos.model.Pokemon
import com.carlos.network.network.ApiClient
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class NetworkMediator @Inject constructor(private val apiClient: ApiClient,private val pokemonDatabase: PokemonDatabase) : RemoteMediator<Int, PokemonEntity>() {


    val pokemonDao = pokemonDatabase.pokemonDao()
    private val remotePageDao = pokemonDatabase.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {

        return try {
            var page = when (loadType) {
                LoadType.REFRESH -> {
                    null
                }
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    Log.d("APPEND","ITS APPEND")
                     val lastItem = state.lastItemOrNull()
                    lastItem?.page
//                   val page = pokemonDatabase.withTransaction{
//                       remotePageDao.getPageKey("APPEND").page
//                   }
//                    page
                }
            }


//            remotePageDao.updatePageKey(
//                RemoteKey(page = page, loadType = "APPEND",id = 10)
//            )

//            Log.d("LOADCALLED","loadcalled")
//            if (page != null){
//                Log.d("PAGE_NOT","NOT NULL")
//                val response = apiClient.fetchPokemonList(page = page)
//                pokemonDatabase.withTransaction {
//                    response.results.map {
//                        it.page = page
//                    }
//                    pokemonDatabase.pokemonDao().insertPokemonList(pokemonList = response.results.asEntity() )
//                }
//            }
//            else {
                val response = apiClient.fetchPokemonList(page = page ?: 1)
                Log.d("ELSE_PART","NOT NULL")
                pokemonDatabase.withTransaction {
                    response.results.map {
                        it.page = page ?: 0
                    }
                    pokemonDatabase.pokemonDao().insertPokemonList(pokemonList = response.results.asEntity() )
                }
           // }




            MediatorResult.Success(page == null)


        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }


    }
}