package com.carlos.database.di

import android.app.Application
import androidx.room.Room
import com.carlos.database.PokemonDatabase
import com.carlos.database.dao.PokemonDao
import com.carlos.database.dao.PokemonInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): PokemonDatabase {
        return Room
            .databaseBuilder(application, PokemonDatabase::class.java, "pokemen.db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun providePokemonDao(
        pokemonDatabase: PokemonDatabase
    ): PokemonDao {
        return pokemonDatabase.pokemonDao()
    }

    @Provides
    @Singleton
    fun providePokemonInfoDao(
        pokemonDatabase: PokemonDatabase
    ):  PokemonInfoDao {
        return pokemonDatabase.pokemonInfoDao()
    }


}