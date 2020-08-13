package com.snor.vf


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.activity_data.*


class DataActivity : AppCompatActivity() {


    private val mHandler: Handler = Handler()
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        initValue()

        //set round background
        data_top_btnlayout.setClipToOutline(true)

        mToastRunnable.run()
        //stop
        //mHandler.removeCallbacks(mToastRunnable)

        data_btn_back.setOnClickListener { backTomain() }
    }

    //constant looping for changing ui data
    private val mToastRunnable: Runnable = object : Runnable {
        override fun run() {

            data_test.setText(i.toString())
            i++

            mHandler.postDelayed(this, 1000)
        }
    }


    fun initValue(){
        val userSettings = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        data_top_macname.text = userSettings.getString("Mac_name","").toString()

    }

    fun nav_click(view: View) {

        if (view.id == R.id.data_nv1) {



        } else if (view.id == R.id.data_nv2) {
            val i = Intent(this, ScheduleActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()

        } else if (view.id == R.id.data_nv3) {
            val i = Intent(this, CameraActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()



        } else if (view.id == R.id.data_nv4) {
            val i = Intent(this, ControlActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()


        } else if (view.id == R.id.data_nv5) {
            val i = Intent(this, AlertActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()

        }

    }

    fun sawp_layout(view: View) {

        if(view.id == R.id.data_btn_chart){
            data_btn_chart.setTextColor(Color.WHITE)
            data_btn_chart.setBackgroundResource(R.color.vf_lightblue)

            data_btn_figure.setTextColor(Color.BLACK)
            data_btn_figure.setBackgroundColor(Color.TRANSPARENT)

        }else if (view.id == R.id.data_btn_figure){

            data_btn_figure.setTextColor(Color.WHITE)
            data_btn_figure.setBackgroundResource(R.color.vf_lightblue)

            data_btn_chart.setTextColor(Color.BLACK)
            data_btn_chart.setBackgroundColor(Color.TRANSPARENT)

        }


    }

    fun backTomain(){
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }

}
