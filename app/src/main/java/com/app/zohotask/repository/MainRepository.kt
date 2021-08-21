package com.app.zohotask.repository

import com.app.zohotask.API.APIClient

class MainRepository constructor(private val retrofitService: APIClient) {
    fun getAPIData() = retrofitService.getAPIData()
}