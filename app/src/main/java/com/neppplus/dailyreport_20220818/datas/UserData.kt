package com.neppplus.dailyreport_20220818.datas

import com.google.gson.annotations.SerializedName

data class UserData(
    val id : Int,
    @SerializedName("nick_name")
    val nickname : String,
    val email : String,
    @SerializedName("profile_img")
    val profileImg : String
)