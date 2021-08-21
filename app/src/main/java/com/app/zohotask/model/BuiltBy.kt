package com.app.zohotask.model

import com.google.gson.annotations.SerializedName


data class BuiltBy (
@SerializedName("href") val href : String,
@SerializedName("avatar") val avatar : String,
@SerializedName("username") val username : String
)
