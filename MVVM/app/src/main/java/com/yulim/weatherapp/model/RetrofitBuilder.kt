package com.yulim.weatherapp.model

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "http://apis.data.go.kr/1360000/TourStnInfoService/"

    private val gson = GsonBuilder().setLenient().create()

    val weatherAppRetrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()

    val mainService : MainService = weatherAppRetrofit.create(MainService::class.java)
}