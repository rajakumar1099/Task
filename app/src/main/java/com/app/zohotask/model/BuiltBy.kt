package com.app.zohotask.model

import androidx.room.ColumnInfo
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

@TypeConverters(TypeConverterOwner::class)
data class BuiltBy (
    @ColumnInfo(name = "href") val href : String?,
    @ColumnInfo(name = "avatar") val avatar : String?,
    @ColumnInfo(name = "username") val username : String?
)


class TypeConverterOwner {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<BuiltBy> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<BuiltBy>>() {

        }.type

        return gson.fromJson<List<BuiltBy>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<BuiltBy>): String {
        return gson.toJson(someObjects)
    }
}
