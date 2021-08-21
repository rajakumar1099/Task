package com.app.zohotask.API

import com.app.zohotask.model.DataModel
import com.app.zohotask.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface APIClient {
    @GET("repositories?since=daily")
    fun getAPIData() : Call<List<DataModel>>
    companion object{
        var retrofitService: APIClient? = null
        fun getInstance(): APIClient{
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(APIClient::class.java)
            }
            return retrofitService!!
        }

    }
}