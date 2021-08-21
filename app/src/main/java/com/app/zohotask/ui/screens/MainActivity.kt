package com.app.zohotask.ui.screens

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.zohotask.API.APIClient
import com.app.zohotask.R
import com.app.zohotask.databinding.ActivityMainBinding
import com.app.zohotask.repository.MainRepository
import com.app.zohotask.ui.adapter.MainAdapter
import com.app.zohotask.viewModel.MainViewModel
import com.app.zohotask.viewModel.MyViewModelFactory


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
        initView()
    }

    private fun initView(){
        binding.toolbar.title = getString(R.string.header_text)
        binding.swipeToRefresh.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this,R.color.purple_700));
        binding.swipeToRefresh.setColorSchemeColors(ContextCompat.getColor(this,R.color.white))
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        setAdapter()
        loadAPI()
        binding.swipeToRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                loadAPI()
            }

        })
    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
    }

    private fun loadAPI() {
        binding.swipeToRefresh.isRefreshing = true
        viewModel.dataList.observe(this, Observer {
            adapter.setDataList(it)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeToRefresh.isRefreshing = false
            }, 1000)
        })
        viewModel.errorMessage.observe(this, Observer {
            binding.swipeToRefresh.isRefreshing = false
        })
        viewModel.getApiData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true
    }


}