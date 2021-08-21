package com.example.zohotask.utils

import com.example.zohotask.model.DataModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ApiInterface {
    @FormUrlEncoded
    @GET("repositories?since=daily")
    fun getAPIData() : Call<List<DataModel>>
}