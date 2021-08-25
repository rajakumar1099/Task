package com.app.zohotask.ui.screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.zohotask.R
import com.app.zohotask.databinding.ActivityMainBinding
import com.app.zohotask.model.DataModel
import com.app.zohotask.ui.adapter.MainAdapter
import com.app.zohotask.utils.AppUtils
import com.app.zohotask.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var TAG: String = this.javaClass.simpleName
    private lateinit var binding: ActivityMainBinding
    private val adapter = MainAdapter()
    lateinit var viewModel: MainViewModel
    val dataList: ArrayList<DataModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
        binding.toolbar.title = getString(R.string.header_text)
        binding.swipeToRefresh.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this,R.color.purple_700));
        binding.swipeToRefresh.setColorSchemeColors(ContextCompat.getColor(this,R.color.white))
        binding.tryAgain.setOnClickListener(this)
        setSupportActionBar(binding.toolbar)
        setAdapter()
        loadAPI()
        binding.swipeToRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                binding.swipeToRefresh.isRefreshing = true
                loadAPI()
            }

        })
    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
    }

    private fun loadAPI() {
        viewModel.getAllRepositoryList().observe(this, Observer<List<DataModel>>{
            dataList.clear()
            dataList.addAll(it)
            adapter.setDataList(dataList)
            if (it.isEmpty() && AppUtils.isNetworkAvailable(this)){
                binding.progressBar.visibility = View.VISIBLE
                binding.nullLay.visibility = View.GONE
            } else if (it.isEmpty() && !AppUtils.isNetworkAvailable(this)){
                binding.progressBar.visibility = View.GONE
                binding.nullLay.visibility = View.VISIBLE
                binding.nullText.text = getString(R.string.no_internet_text)
            } else {
                binding.progressBar.visibility = View.GONE
                binding.nullLay.visibility = View.GONE
            }
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeToRefresh.isRefreshing = false
            }, 2000)

        })
        viewModel.makeApiCall()
    }

    private fun filter(text: String){
        val filterList: ArrayList<DataModel> = ArrayList()
        filterList.clear()
        dataList.forEach{
            if (it.name.lowercase().contains(text.lowercase())) {
                filterList.add(it)
            }else if(text.isEmpty()){
                filterList.addAll(dataList)
            }
        }
        adapter.setDataList(filterList)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val menuItem: MenuItem = menu!!.findItem(R.id.search)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnSearchClickListener(){
            binding.swipeToRefresh.isEnabled = false
            binding.swipeToRefresh.isRefreshing = false
        }
        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                binding.swipeToRefresh.isEnabled = true
                return false
            }
        })
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(newText: String?): Boolean {
                filter(newText!!)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText!!)
                return false
            }
        })
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tryAgain->{
                loadAPI()
            }
        }
    }


}