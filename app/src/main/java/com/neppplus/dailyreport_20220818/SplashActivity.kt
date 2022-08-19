package com.neppplus.dailyreport_20220818

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.neppplus.dailyreport_20220818.datas.BasicResponse
import com.neppplus.dailyreport_20220818.utils.ContextUtil
import com.neppplus.dailyreport_20220818.utils.GlobalData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        val email = "test@test.com"
        val password = "Test!123"

        apiList.postRequestLogin(email, password).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {

                    val br = response.body()!!

                    ContextUtil.setLoginToken(mContext, br.data.token)
                    GlobalData.loginUser = br.data.user

//                    Log.d("닉네임", GlobalData.loginUser!!.nickname)

                    val myIntent = Intent(mContext, MainActivity::class.java)
                    startActivity(myIntent)
                    finish()

                }
                else {
                    val errorBodyStr = response.errorBody()!!.string()
                    val jsonObj = JSONObject(errorBodyStr)
                    val message = jsonObj.getString("message")

                    Log.d("message", message)
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }
}