package com.snor.vf.Class



import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


const val TAG ="VF-Message"
class MyFirebaseMessagingService:FirebaseMessagingService() {

    var NOTIFICATION_CHANNEL_ID = "com.snor.vf"
    val NOTIFICATION_ID = 100

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)

        Log.e("message","Message Received")

    }


    override fun onNewToken(p0:String){
        super.onNewToken(p0)
        Log.e("token","New token")
    }
}