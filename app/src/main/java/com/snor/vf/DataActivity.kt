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
import kotlinx.android.synthetic.main.activity_schedule.*


class DataActivity : AppCompatActivity() {


    var mac_id : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        initValue()

        //set round background
        data_top_btnlayout.setClipToOutline(true)

        data_btn_back.setOnClickListener { backTomain() }

        val bundle = Bundle()
        bundle.putString("ID", mac_id)
        val trans = supportFragmentManager.beginTransaction()
        val fragment = Data_CFragment()
        fragment.arguments = bundle
        trans.addToBackStack(null)
        trans.replace(R.id.data_frag_layout,fragment)
        trans.commit()


    }




    fun initValue(){
        val userSettings = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        mac_id = userSettings.getString("Mac_id","").toString()
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
