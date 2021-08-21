package com.example.zohotask.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zohotask.model.DataModel
import com.example.zohotask.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val dataList = MutableLiveData<List<DataModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getApiData() {
        val response = repository.getAPIData()
        response.enqueue(object : Callback<List<DataModel>> {
            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                dataList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}