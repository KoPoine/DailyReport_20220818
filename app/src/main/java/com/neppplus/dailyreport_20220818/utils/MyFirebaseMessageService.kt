package com.neppplus.dailyreport_20220818.utils

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessageService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
//        알림 오면 할 일 작성

//        data-message 양식으로 왔을 경우
        if (message.data.isNotEmpty()) {
            Log.d("FCM", "data : ${message.data}")
        }

//        notification 양식으로 왔을 경우
        message.notification?.let {
            Log.d("FCM", "notification : ${it.body}")


//            백그라운드 쓰레드 내부이므로, UI 쓰레드에게 UI 조작 코드 전달
//                => Handler를 getMainLooper로 활용해서 post { }로 할일 전달
           Handler(Looper.getMainLooper()).post {
                Toast.makeText(this, "notification 알림 수신", Toast.LENGTH_SHORT).show()
            }


        }

    }

}