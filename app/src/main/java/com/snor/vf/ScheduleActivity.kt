package com.snor.vf

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_schedule.*

class ScheduleActivity : AppCompatActivity() {

    var mac_id : String? = ""


    override fun onStart() {
        super.onStart()

        //set round layout
        schedule_top_btnlayout.setClipToOutline(true)

        //start fragment
        initStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)


        //back button
        schedule_btn_back.setOnClickListener { backTomain() }

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

        val userSettings = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val prefEditor = userSettings.edit()

        if(view.id == R.id.schedule_btn_schedule){
            schedule_btn_schedule.setTextColor(Color.WHITE)
            schedule_btn_schedule.setBackgroundResource(R.color.vf_lightblue)

            schedule_btn_manual.setTextColor(Color.BLACK)
            schedule_btn_manual.setBackgroundColor(Color.TRANSPARENT)

            val bundle = Bundle()
            bundle.putString("ID", mac_id)
            val trans = supportFragmentManager.beginTransaction()
            val fragment = Schedule_sFragment()
            fragment.arguments = bundle
            trans.addToBackStack(null)
            trans.replace(R.id.schedule_frag_layout,fragment)
            trans.commit()

            val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id/ControlMode")
            db.child("fan_mode").setValue(1)
            db.child("pump_mode").setValue(1)
            db.child("light_mode").setValue(1)

            prefEditor.putString("f_mode", "1")
            prefEditor.putString("l_mode", "1")
            prefEditor.putString("p_mode", "1")
            prefEditor.apply()


            Toast.makeText(this,"Switch To Auto Mode",Toast.LENGTH_SHORT).show()



        }else if (view.id == R.id.schedule_btn_manual){

            schedule_btn_manual.setTextColor(Color.WHITE)
            schedule_btn_manual.setBackgroundResource(R.color.vf_lightblue)

            schedule_btn_schedule.setTextColor(Color.BLACK)
            schedule_btn_schedule.setBackgroundColor(Color.TRANSPARENT)

            val bundle = Bundle()
            bundle.putString("ID", mac_id)
            val trans = supportFragmentManager.beginTransaction()
            val fragment = Schedule_mFragment()
            fragment.arguments = bundle
            trans.addToBackStack(null)
            trans.replace(R.id.schedule_frag_layout,fragment)
            trans.commit()

            val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id/ControlMode")
            db.child("fan_mode").setValue(0)
            db.child("pump_mode").setValue(0)
            db.child("light_mode").setValue(0)

            prefEditor.putString("f_mode", "0")
            prefEditor.putString("l_mode", "0")
            prefEditor.putString("p_mode", "0")
            prefEditor.apply()


            Toast.makeText(this,"Switch To Manual Mode",Toast.LENGTH_SHORT).show()

        }

    }

    fun backTomain(){
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }

    fun initStart(){

        val userSettings = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        mac_id = userSettings.getString("Mac_id","").toString()
        schedule_top_macname.text = userSettings.getString("Mac_name","").toString()

        val f_mode = userSettings.getString("f_mode","").toString().toInt()
        val l_mode = userSettings.getString("l_mode","").toString().toInt()
        val p_mode = userSettings.getString("p_mode","").toString().toInt()


        if (f_mode == 1 || l_mode == 1 || p_mode == 1){

            schedule_btn_schedule.setTextColor(Color.WHITE)
            schedule_btn_schedule.setBackgroundResource(R.color.vf_lightblue)

            schedule_btn_manual.setTextColor(Color.BLACK)
            schedule_btn_manual.setBackgroundColor(Color.TRANSPARENT)

            val bundle = Bundle()
            bundle.putString("ID", mac_id)
            val trans = supportFragmentManager.beginTransaction()
            val fragment = Schedule_sFragment()
            fragment.arguments = bundle
            trans.addToBackStack(null)
            trans.replace(R.id.schedule_frag_layout,fragment)
            trans.commit()

        }else if (f_mode == 0|| l_mode == 0 || p_mode == 0){

            schedule_btn_manual.setTextColor(Color.WHITE)
            schedule_btn_manual.setBackgroundResource(R.color.vf_lightblue)

            schedule_btn_schedule.setTextColor(Color.BLACK)
            schedule_btn_schedule.setBackgroundColor(Color.TRANSPARENT)


            val bundle = Bundle()
            bundle.putString("ID", mac_id)
            val trans = supportFragmentManager.beginTransaction()
            val fragment = Schedule_mFragment()
            fragment.arguments = bundle
            trans.addToBackStack(null)
            trans.replace(R.id.schedule_frag_layout,fragment)
            trans.commit()
        }



    }

}