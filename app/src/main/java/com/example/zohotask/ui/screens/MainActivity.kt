package com.example.zohotask.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.zohotask.API.APIClient
import com.example.zohotask.R
import com.example.zohotask.databinding.ActivityMainBinding
import com.example.zohotask.repository.MainRepository
import com.example.zohotask.ui.adapter.MainAdapter
import com.example.zohotask.viewModel.MainViewModel
import com.example.zohotask.viewModel.MyViewModelFactory


class MainActivity : AppCompatActivity() {
    private var TAG: String = this.javaClass.simpleName
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = APIClient.getInstance()
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = getString(R.string.header_text)
        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        setAdapter()
        loadAPI()
    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
    }

    private fun loadAPI() {
        viewModel.dataList.observe(this, Observer {
            adapter.setDataList(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.errorMessage.observe(this, Observer {
        })
        viewModel.getApiData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true
    }


}