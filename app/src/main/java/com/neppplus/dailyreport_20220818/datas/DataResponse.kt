package com.neppplus.dailyreport_20220818.datas

data class DataResponse(
    val user : UserData,
    val token : String,
    val feeds : List<FeedData>
)
