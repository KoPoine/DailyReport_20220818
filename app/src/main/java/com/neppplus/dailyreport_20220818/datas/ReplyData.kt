package com.neppplus.dailyreport_20220818.datas

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class ReplyData(
    val id : Int,
    val content : String,
    @SerializedName("created_at")
    val createdAt : Date
) : Serializable
