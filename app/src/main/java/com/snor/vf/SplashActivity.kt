package com.snor.vf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            {
                val i = Intent(this, MainActivity::class.java)
                finish()
                overridePendingTransition( R.anim.ani_up, R.anim.ani_down )
                startActivity(i)
            }, 1000)


    }
}