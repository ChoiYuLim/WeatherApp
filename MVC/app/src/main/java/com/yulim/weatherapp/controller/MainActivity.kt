package com.yulim.weatherapp.controller

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.yulim.weatherapp.BuildConfig.serviceKey
import com.yulim.weatherapp.databinding.ActivityMainBinding
import com.yulim.weatherapp.model.RetrofitBuilder
import com.yulim.weatherapp.sigunguAdapter
import com.yulim.weatherapp.sigunguData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {
    private lateinit var sigungu_adapter: sigunguAdapter
    private lateinit var binding: ActivityMainBinding

    val sigunguMap: MutableMap<String, String> = mutableMapOf()
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd0000")
    val formatted = current.format(formatter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        initMap()
        initAdapter()
    }

    private fun initListener() {
        binding.etSigungu.setOnFocusChangeListener { it, hasFocus ->
            if (hasFocus) {
                binding.rvSigungu.visibility = View.VISIBLE
                binding.btnSearch.visibility = View.INVISIBLE
            } else {
                binding.rvSigungu.visibility = View.INVISIBLE
                binding.btnSearch.visibility = View.VISIBLE
            }
        }

        binding.btnSearch.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val info = RetrofitBuilder.mainService.getTourismClimate(
                        serviceKey = serviceKey,
                        1,
                        1,
                        "JSON",
                        formatted,
                        0,
                        sigunguMap[binding.etSigungu.text.toString()].toString()
                    ).body()!!.response.body.items.item
                    binding.tvWeatherIndices.text = info[0].kmaTci
                    binding.tvWeatherLevel.text = info[0].tciGrade.toString()
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "존재하지 않는 시군구입니다.", Toast.LENGTH_SHORT).show()
                    binding.tvWeatherIndices.text = ""
                    binding.tvWeatherLevel.text = ""
                }
            }
        }
    }

    private fun initAdapter() {
        sigungu_adapter = sigunguAdapter()

        binding.rvSigungu.adapter = sigungu_adapter

        for (key in sigunguMap.keys) {
            sigungu_adapter.sigunguList.add(
                sigunguData(key)
            )
        }

        sigungu_adapter.notifyDataSetChanged()
    }

    private fun initMap() {
        sigunguMap["강남구"] = "1168000000"
        sigunguMap["강동구"] = "1174000000"
        sigunguMap["강북구"] = "1130500000"
        sigunguMap["강서구"] = "1150000000"
        sigunguMap["관악구"] = "1162000000"
        sigunguMap["광진구"] = "1121500000"
        sigunguMap["구로구"] = "1153000000"
        sigunguMap["금천구"] = "1154500000"
        sigunguMap["노원구"] = "1135000000"
        sigunguMap["도봉구"] = "1132000000"
        sigunguMap["동대문구"] = "1123000000"
        sigunguMap["동작구"] = "1159000000"
        sigunguMap["마포구"] = "1144000000"
        sigunguMap["서대문구"] = "1141000000"
        sigunguMap["서초구"] = "1165000000"
        sigunguMap["성동구"] = "1120000000"
        sigunguMap["성북구"] = "1129000000"
        sigunguMap["송파구"] = "1171000000"
        sigunguMap["양천구"] = "1147000000"
        sigunguMap["영등포구"] = "1156000000"
        sigunguMap["용산구"] = "1117000000"
        sigunguMap["은평구"] = "1138000000"
        sigunguMap["종로구"] = "1111000000"
        sigunguMap["중구"] = "1114000000"
        sigunguMap["중랑구"] = "1126000000"
    }
}