package com.app.zohotask.API

import com.app.zohotask.model.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("repositories?since=daily")
    fun getDataFromAPI(): Call<List<DataModel>>
}