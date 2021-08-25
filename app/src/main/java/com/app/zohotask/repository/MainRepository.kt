package com.app.zohotask.repository

import androidx.lifecycle.LiveData
import com.app.zohotask.API.APIInterface
import com.app.zohotask.db.AppDao
import com.app.zohotask.model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val retroServiceInterface: APIInterface, private val appDao: AppDao) {
    fun getAllRecords(): LiveData<List<DataModel>> {
        return appDao.getAllRecords()
    }

    fun insertRecord(dataModel: List<DataModel>) {
        appDao.insertRecords(dataModel)
    }

    fun deleteAllRecords(){
        appDao.deleteAllRecords()
    }

    //get the data from github api...
    fun makeApiCall() {
        val call: Call<List<DataModel>> = retroServiceInterface.getDataFromAPI()
        call?.enqueue(object : Callback<List<DataModel>> {
            override fun onResponse(
                call: Call<List<DataModel>>,
                response: Response<List<DataModel>>) {
                if(response.isSuccessful) {
                    deleteAllRecords()
                    insertRecord(response.body()!!)
                }
            }
            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {

            }
        })
    }}