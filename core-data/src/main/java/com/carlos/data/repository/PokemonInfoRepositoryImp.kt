package com.carlos.data.repository

import com.carlos.database.dao.PokemonInfoDao
import com.carlos.database.mapper.asDomain
import com.carlos.database.mapper.asEntity
import com.carlos.model.PokemonInfo
import com.carlos.network.network.ApiCallResult
import com.carlos.network.network.ApiClient
import javax.inject.Inject


class PokemonInfoRepositoryImp @Inject constructor(private val apiClient: ApiClient, private val  pokemonInfoDao: PokemonInfoDao) : PokemonInfoRepository {

    override suspend fun fetchPokemonInfo(name: String): PokemonInfo? {
        val pokemonInfo = pokemonInfoDao.getPokemonInfo(name_ = name)
        if ( pokemonInfo == null){
            return when(val result = apiClient.fetchPokemonInfo(name)){
                is ApiCallResult.ServerError -> {
                    null
                }
                is ApiCallResult.ApiCallError -> {
                    null
                }
                is ApiCallResult.Success -> {
                    pokemonInfoDao.insertPokemonInfo(result.data.asEntity())
                    result.data
                }
            }
        }
        else {
            return  pokemonInfo.asDomain()
        }
    }
}