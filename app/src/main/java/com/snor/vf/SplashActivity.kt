package com.snor.vf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getToken()

        Handler().postDelayed(
            {
                val i = Intent(this, MainActivity::class.java)
                finish()
                overridePendingTransition( R.anim.ani_up, R.anim.ani_down )
                startActivity(i)
            }, 1000)


    }


    fun getToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast

            Log.d("TOKEN", token.toString())


            val ref = FirebaseDatabase.getInstance().getReference("OTHER/MAC0/TOKEN/token")
            ref.setValue(token.toString())



        })



    }


}