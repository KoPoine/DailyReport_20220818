package com.neppplus.dailyreport_20220818.datas

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class FeedData(
    val id : Int,
    @SerializedName("total_concentrated_seconds")
    val totalConSecond : Int,
    @SerializedName("open_concentrated_time")
    val openConTime : Boolean,
    val content : String,
    val user : UserData,
    @SerializedName("created_at")
    val createdAt : Date,
    val images : List<String>,
    val replies : List<ReplyData>
) : Serializable