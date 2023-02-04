package com.carlos.database.converters

import androidx.room.TypeConverter
import com.carlos.model.TypeResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class TypeResponseConvertor {


    @TypeConverter
    fun fromString(value: String?): List<TypeResponse> {
        val listType: java.lang.reflect.Type = object :
            TypeToken<List<TypeResponse>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listToString(list: List<TypeResponse>): String {
        val gson = Gson()
        return gson.toJson(list)
    }


}

