package com.snor.vf

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.activity_schedule.*

class ScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        //set round layout
        schedule_top_btnlayout.setClipToOutline(true)

        //start fragment
        val trans = supportFragmentManager.beginTransaction()
        val fragment = Schedule_sFragment()
        trans.addToBackStack(null)
        trans.replace(R.id.schedule_frag_layout,fragment)
        trans.commit()



    }

    fun nav_click(view: View) {

        if(view.id == R.id.schedule_nv1){

            val i = Intent(this, DataActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()

        }else if (view.id == R.id.schedule_nv2){


        }else if (view.id == R.id.schedule_nv3){
            val i = Intent(this, CameraActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()

        }else if (view.id == R.id.schedule_nv4){
            val i = Intent(this, ControlActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()


        }else if (view.id == R.id.schedule_nv5){
            val i = Intent(this, AlertActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()


        }


    }

    fun sawp_layout(view: View) {

        if(view.id == R.id.schedule_btn_schedule){
            schedule_btn_schedule.setTextColor(Color.WHITE)
            schedule_btn_schedule.setBackgroundResource(R.color.vf_lightblue)

            schedule_btn_manual.setTextColor(Color.BLACK)
            schedule_btn_manual.setBackgroundColor(Color.TRANSPARENT)


            val trans = supportFragmentManager.beginTransaction()
            val fragment = Schedule_sFragment()
            trans.addToBackStack(null)
            trans.replace(R.id.schedule_frag_layout,fragment)
            trans.commit()



        }else if (view.id == R.id.schedule_btn_manual){

            schedule_btn_manual.setTextColor(Color.WHITE)
            schedule_btn_manual.setBackgroundResource(R.color.vf_lightblue)

            schedule_btn_schedule.setTextColor(Color.BLACK)
            schedule_btn_schedule.setBackgroundColor(Color.TRANSPARENT)

            val trans = supportFragmentManager.beginTransaction()
            val fragment = Schedule_mFragment()
            trans.addToBackStack(null)
            trans.replace(R.id.schedule_frag_layout,fragment)
            trans.commit()


        }

    }
}