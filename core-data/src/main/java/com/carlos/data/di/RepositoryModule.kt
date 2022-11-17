package com.carlos.data.di

import com.carlos.data.repository.MainRepository
import com.carlos.data.repository.MainRepositoryImp
import com.carlos.data.repository.PokemonInfoRepository
import com.carlos.data.repository.PokemonInfoRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsMainRepository(mainRepositoryImp: MainRepositoryImp): MainRepository


    @Binds
    fun bindsPokemonInfoRepository(pokemonInfoRepositoryImp: PokemonInfoRepositoryImp): PokemonInfoRepository
}
