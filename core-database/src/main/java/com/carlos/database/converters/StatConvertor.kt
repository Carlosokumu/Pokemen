package com.carlos.database.converters

import androidx.room.TypeConverter
import com.carlos.model.Stats
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StatConvertor {

    @TypeConverter
    fun fromString(value: String?): List<Stats> {
        val listType: java.lang.reflect.Type = object :
            TypeToken<List<Stats>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<Stats>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}