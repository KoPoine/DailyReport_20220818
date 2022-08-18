package com.neppplus.dailyreport_20220818.api

import com.neppplus.dailyreport_20220818.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIList {
    @GET("/feed")
    fun getRequestFeed(): Call<BasicResponse>

    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email : String,
        @Field("password") password : String
    ): Call<BasicResponse>
}