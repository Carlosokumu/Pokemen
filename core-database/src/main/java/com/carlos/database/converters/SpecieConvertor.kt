package com.carlos.database.converters

import androidx.room.TypeConverter
import com.carlos.model.Specie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpecieConvertor {


    @TypeConverter
    fun fromString(value: String?): Specie {
        val specieType: java.lang.reflect.Type = object :
            TypeToken<Specie>() {}.type
        return Gson().fromJson(value, specieType)
    }


    @TypeConverter
    fun specieToString(specie: Specie): String {
        val gson = Gson()
        return gson.toJson(specie)
    }
}