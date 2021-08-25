package com.app.zohotask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.zohotask.model.DataModel
import com.app.zohotask.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository)
    : ViewModel() {


    fun getAllRepositoryList(): LiveData<List<DataModel>> {
        return repository.getAllRecords()
    }

    fun makeApiCall() {
        repository.makeApiCall()
    }

}