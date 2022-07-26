package com.yulim.weatherapp.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {
    @GET("getCityTourClmIdx")
    suspend fun getTourismClimate(
        @Query("serviceKey",encoded = true) serviceKey: String,
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
        @Query("dataType") dataType: String,
        @Query("CURRENT_DATE") CURRENT_DATE: String,
        @Query("DAY") DAY: Int,
        @Query("CITY_AREA_ID") CITY_AREA_ID : String
    ): Response<MainDao>
}