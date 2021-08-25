package com.app.zohotask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "repositories")
data class DataModel (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")val id: Int = 0,
    @ColumnInfo(name = "author") val author : String,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "avatar") val avatar : String,
    @ColumnInfo(name = "url") val url : String,
    @ColumnInfo(name = "description") val description : String,
    @ColumnInfo(name = "language") val language : String,
    @ColumnInfo(name = "languageColor") val languageColor : String,
    @ColumnInfo(name = "stars") val stars : Int,
    @ColumnInfo(name = "forks") val forks : Int,
    @ColumnInfo(name = "currentPeriodStars") val currentPeriodStars : Int,

    @TypeConverters(TypeConverterOwner::class)
    @ColumnInfo(name = "builtBy") val builtBy : List<BuiltBy>
)