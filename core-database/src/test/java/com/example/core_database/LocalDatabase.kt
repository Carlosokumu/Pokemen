package com.example.core_database

import androidx.room.Room
import com.carlos.database.PokemonDatabase
import com.google.gson.Gson
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
abstract class LocalDatabase {
    lateinit var db: PokemonDatabase


    @Before
    fun initDB() {

        val gson = Gson()
        db = Room.inMemoryDatabaseBuilder(getApplicationContext(), PokemonDatabase::class.java)
            .allowMainThreadQueries()
            .addTypeConverter(gson)
            .build()
    }

    @After
    fun closeDB() {
        db.close()
    }
}