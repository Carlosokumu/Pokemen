package com.carlos.pokemen.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.carlos.network.models.Pokemon
import com.carlos.network.network.ApiClient
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class NetworkMediator(private val apiClient: ApiClient) : RemoteMediator<Int, Pokemon>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Pokemon>
    ): MediatorResult {

        return try {
           when (loadType) {
                LoadType.REFRESH -> null
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {

                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )

                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.

                    // lastItem.id
                    val nextPage = lastItem.page++
                }
            }
            val pokemonList = apiClient.fetchPokemonList()
            MediatorResult.Success(true)



        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }


    }
}