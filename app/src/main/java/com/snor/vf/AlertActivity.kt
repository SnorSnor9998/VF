package com.snor.vf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_alert.*

class AlertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert)

        alert_btn_back.setOnClickListener { backTomain() }
    }

    fun nav_click(view: View) {


        if (view.id == R.id.alert_nv1) {
            val i = Intent(this, DataActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()


        } else if (view.id == R.id.alert_nv2) {
            val i = Intent(this, ScheduleActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()

        } else if (view.id == R.id.alert_nv3) {
            val i = Intent(this, CameraActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()



        } else if (view.id == R.id.alert_nv4) {
            val i = Intent(this, ControlActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()


        } else if (view.id == R.id.alert_nv5) {


        }


    }

    fun backTomain(){
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }
}