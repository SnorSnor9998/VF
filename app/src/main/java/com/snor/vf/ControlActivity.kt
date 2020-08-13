package com.snor.vf

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_control.*
import kotlinx.android.synthetic.main.activity_schedule.*

class ControlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)

        //set round layout
        control_top_btnlayout.setClipToOutline(true)

        //start fragment
        val trans = supportFragmentManager.beginTransaction()
        val fragment = Control_aFragment()
        trans.addToBackStack(null)
        trans.replace(R.id.control_frag_layout,fragment)
        trans.commit()

        //back button
        control_btn_back.setOnClickListener { backTomain() }

    }

    fun nav_click(view: View) {

        if (view.id == R.id.control_nv1) {
            val i = Intent(this, DataActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()

        } else if (view.id == R.id.control_nv2) {
            val i = Intent(this, ScheduleActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()

        } else if (view.id == R.id.control_nv3) {
            val i = Intent(this, CameraActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()

        } else if (view.id == R.id.control_nv4) {



        } else if (view.id == R.id.control_nv5) {
            val i = Intent(this, AlertActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()

        }


    }

    fun sawp_layout(view: View) {

        if(view.id == R.id.control_top_btn_auto){
            control_top_btn_auto.setTextColor(Color.WHITE)
            control_top_btn_auto.setBackgroundResource(R.color.vf_lightblue)

            control_top_btn_manual.setTextColor(Color.BLACK)
            control_top_btn_manual.setBackgroundColor(Color.TRANSPARENT)


            val trans = supportFragmentManager.beginTransaction()
            val fragment = Control_aFragment()
            trans.addToBackStack(null)
            trans.replace(R.id.control_frag_layout,fragment)
            trans.commit()



        }else if (view.id == R.id.control_top_btn_manual){

            control_top_btn_manual.setTextColor(Color.WHITE)
            control_top_btn_manual.setBackgroundResource(R.color.vf_lightblue)

            control_top_btn_auto.setTextColor(Color.BLACK)
            control_top_btn_auto.setBackgroundColor(Color.TRANSPARENT)

            val trans = supportFragmentManager.beginTransaction()
            val fragment = Control_mFragment()
            trans.addToBackStack(null)
            trans.replace(R.id.control_frag_layout,fragment)
            trans.commit()


        }


    }

    fun backTomain(){
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }
}