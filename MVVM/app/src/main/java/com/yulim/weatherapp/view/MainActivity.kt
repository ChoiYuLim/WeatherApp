package com.yulim.weatherapp.view

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.yulim.weatherapp.R
import com.yulim.weatherapp.databinding.ActivityMainBinding
import com.yulim.weatherapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            viewModel = MainViewModel()
        }
        initListener()
        initAdapter()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initListener() {
        binding.viewModel!!.initMap()

        binding.etSigungu.setOnEditorActionListener { textView, i, keyEvent ->
            binding.etSigungu.clearFocus()
            false
        }

        binding.viewModel!!.status.observe(this, Observer {
            if(it){
                binding.tvWeatherIndices.visibility = View.VISIBLE
                binding.tvWeatherLevel.visibility = View.VISIBLE
            }else{
                binding.tvWeatherIndices.visibility = View.INVISIBLE
                binding.tvWeatherLevel.visibility = View.INVISIBLE
            }
        })
    }

    private fun initAdapter() {
        var items = arrayListOf<String>()
        for (key in binding.viewModel!!.sigunguMap.keys) {
            items.add(key)
        }
        var adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            items
        )
        binding.etSigungu.setAdapter(adapter)
    }
}