package com.example.zohotask.API

import com.example.zohotask.model.DataModel
import com.example.zohotask.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface APIClient {
    @GET("repositories?since=daily")
    fun getAPIData() : Call<List<DataModel>>
    companion object{
        var retrofitService: APIClient? = null
        fun getInstance(): APIClient{
            if (retrofitService == null) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val client: okhttp3.OkHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .callTimeout(60000, TimeUnit.SECONDS)
                    .connectTimeout(60000, TimeUnit.SECONDS)
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(APIClient::class.java)
            }
            return retrofitService!!
        }

    }
}