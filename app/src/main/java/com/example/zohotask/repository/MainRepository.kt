package com.example.zohotask.repository

import com.example.zohotask.API.APIClient
import com.example.zohotask.utils.ApiInterface

class MainRepository constructor(private val retrofitService: APIClient) {
    fun getAPIData() = retrofitService.getAPIData()
}