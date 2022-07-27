package com.mohammadhashem.lastnews.data.database.typeconvertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mohammadhashem.lastnews.data.model.SourceX

class Converters {
//    @TypeConverter
//    fun toSourseXType(value: String): SourceX {
//        return SourceX.valueOf(value)
//    }
//
//    @TypeConverter
//    fun fromSourseXType(value: SourceX): String {
//        return value.name
//    }

    @TypeConverter
    fun fromString(value: String): SourceX {
        val listType = object : TypeToken<SourceX>() {}.type
        return Gson().fromJson<SourceX>(value, listType)
    }

    @TypeConverter
    fun fromList(list: SourceX): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}