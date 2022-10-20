package com.carlos.data.di

import com.carlos.data.MainRepository
import com.carlos.data.MainRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsMainRepository(mainRepositoryImp: MainRepositoryImp): MainRepository
}
