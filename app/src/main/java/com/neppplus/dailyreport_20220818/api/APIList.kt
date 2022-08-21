package com.neppplus.dailyreport_20220818.api

import com.neppplus.dailyreport_20220818.datas.BasicResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface APIList {
    @GET("/feed")
    fun getRequestFeed(
        @Query("page_num") pageNum : Int
    ): Call<BasicResponse>

    @FormUrlEncoded
    @POST("/user")
    fun postRequestLogin(
        @Field("email") email : String,
        @Field("password") password : String
    ): Call<BasicResponse>

    @Multipart
    @PUT("/user")
    fun putRequestSignUp (
        @PartMap map: HashMap<String, RequestBody>,
        @Part profileImg : MultipartBody.Part?
    ) : Call<BasicResponse>
}