package com.neppplus.dailyreport_20220818.datas

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat
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
) : Serializable {

    fun getTimeTxt() : String {
        val now = Calendar.getInstance()   // 현재 일시
        val distance = now.timeInMillis - this.createdAt.time   // 두 일시간의 차이(시간을 양으로 나타낸)

        return when {
//                1분보다 짧다면 => "방금 전"
            distance < 60000 -> "방금 전"

//                1시간 보다 짧다면 => 분 단위로 표시
            distance < 3600000 -> "${distance / 60000}분 전"

//                24시간보다 짧다면 => 시간단위로 표시
            distance < 86400000 -> "${distance / 3600000}시간 전"

//                어제 제작된 피드라면(24시간을 넘은) => 하루 전
            (distance / 86400000 == 1L) -> "하루 전"

//                나머지는 원래 양식으로 표시("yy.MM.dd a h:ss")
            else -> {
                val sdf = SimpleDateFormat("yy.MM.dd a h:ss")
                sdf.format(this.createdAt)
            }
        }
    }
}