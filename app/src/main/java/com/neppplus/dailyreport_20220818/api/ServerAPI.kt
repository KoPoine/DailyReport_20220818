package com.neppplus.dailyreport_20220818.api

import android.content.Context
import com.google.gson.GsonBuilder
import com.neppplus.dailyreport_20220818.utils.ContextUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerAPI {

    companion object {
        private val baseUrl = "http://3.37.32.230/"

        private var retrofit : Retrofit? = null

        fun getRetrofit(context : Context) : Retrofit {
            if (retrofit == null) {

//                자동으로 만드는 Request / client를 커스터 마이징하자

//                API 요청이 발생하면 => 가로채서(interceptor) => 헤더를 추가하자.
//                자동으로 헤더를 달아주는 효과 발생

                val interceptor = Interceptor{
                    with(it) {
                        val newRequest = request().newBuilder()
                            .addHeader("X-Http-Token", ContextUtil.getLoginToken(context))
                            .build()
                        proceed(newRequest)
                    }
                }

//                gson에게 날짜 양식을 어떻게 파싱할 건지 => 추가 기능을 가진 gson으로 생성
                val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create()

//                retrofit은 OkHttp의 확장판 => OkHttpClient 형태의 클라이언트를 활용
//                클라이언트에게 우리가 만든 인터셉터를 달아주자.(클라이언트 커스터마이징)
                val myClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(myClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return retrofit!!
        }

    }

}