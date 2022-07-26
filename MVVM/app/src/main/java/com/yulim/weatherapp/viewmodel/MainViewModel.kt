package com.yulim.weatherapp.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yulim.weatherapp.BuildConfig.serviceKey
import com.yulim.weatherapp.model.Item
import com.yulim.weatherapp.model.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel {
    val sigungu = MutableLiveData<String>()
    val Item = ObservableField<Item>()
    val _status = MutableLiveData<Boolean>(false)
    val status: LiveData<Boolean> = _status

    val sigunguMap: MutableMap<String, String> = mutableMapOf()
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd0000")
    val formatted = current.format(formatter)

    fun initMap() {
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

    fun callItem() {
        if (!sigunguMap.contains(sigungu.value.toString())) {
            _status.postValue(false)
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val info = RetrofitBuilder.mainService.getTourismClimate(
                        serviceKey = serviceKey,
                        1,
                        1,
                        "JSON",
                        formatted,
                        0,
                        sigunguMap[sigungu.value.toString()].toString()
                    ).body()!!.response.body.items.item[0]
                    Item.set(info)
                    _status.postValue(true)
                } catch (e: Exception) {
                    _status.postValue(false)
                }
            }
        }
    }
}