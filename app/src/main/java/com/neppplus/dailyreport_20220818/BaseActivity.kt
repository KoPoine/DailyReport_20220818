package com.neppplus.dailyreport_20220818

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neppplus.dailyreport_20220818.api.APIList
import com.neppplus.dailyreport_20220818.api.ServerAPI
import retrofit2.Retrofit
import retrofit2.create

abstract class BaseActivity: AppCompatActivity() {

    lateinit var mContext: Context
    lateinit var retrofit : Retrofit
    lateinit var apiList : APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        retrofit = ServerAPI.getRetrofit(mContext)
        apiList = retrofit.create(APIList::class.java)
    }

    abstract fun setupEvents()
    abstract fun setValues()

}