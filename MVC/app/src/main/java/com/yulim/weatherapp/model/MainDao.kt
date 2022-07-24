package com.yulim.weatherapp.model

data class MainDao(val response: Response)

data class Response(
    val body: Body,
    val header: Header
)

data class Header(
    val resultMsg: String,
    val resultCode: String
)

data class Body(
    val dataType: String,
    val items: Items,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

data class Items(
    val item: List<Item>
)

data class Item(
    val tm: String,
    val totalCityName: String,
    val doName: String,
    val cityName: String,
    val cityAreaId: String,
    val kmaTci: String,
    val tciGrade: Int
)
