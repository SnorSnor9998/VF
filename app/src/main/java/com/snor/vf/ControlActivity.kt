package com.snor.vf

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_control.*
import kotlinx.android.synthetic.main.activity_schedule.*

class ControlActivity : AppCompatActivity() {

    var mac_id : String? = ""

    override fun onStart() {
        super.onStart()
        initValue()

        //set round layout
        control_top_btnlayout.setClipToOutline(true)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)



        //start fragment
        val trans = supportFragmentManager.beginTransaction()
        val fragment = Control_aFragment()
        trans.addToBackStack(null)
        trans.replace(R.id.control_frag_layout,fragment)
        trans.commit()

        //back button
        control_btn_back.setOnClickListener { backTomain() }

    }

    fun initValue(){
        val userSettings = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        mac_id = userSettings.getString("Mac_id","").toString()

        control_top_macname.text = "ID: "+ mac_id

        val a_mode = userSettings.getString("a_mode","").toString().toInt()

        if (a_mode == 1){

            control_top_btn_auto.setTextColor(Color.WHITE)
            control_top_btn_auto.setBackgroundResource(R.color.vf_lightblue)

            control_top_btn_manual.setTextColor(Color.BLACK)
            control_top_btn_manual.setBackgroundColor(Color.TRANSPARENT)

            val bundle = Bundle()
            bundle.putString("ID", mac_id)
            val trans = supportFragmentManager.beginTransaction()
            val fragment = Control_aFragment()
            fragment.arguments = bundle
            trans.addToBackStack(null)
            trans.replace(R.id.control_frag_layout,fragment)
            trans.commit()


        }else if (a_mode == 0){

            control_top_btn_manual.setTextColor(Color.WHITE)
            control_top_btn_manual.setBackgroundResource(R.color.vf_lightblue)

            control_top_btn_auto.setTextColor(Color.BLACK)
            control_top_btn_auto.setBackgroundColor(Color.TRANSPARENT)


            val bundle = Bundle()
            bundle.putString("ID", mac_id)
            val trans = supportFragmentManager.beginTransaction()
            val fragment = Control_mFragment()
            fragment.arguments = bundle
            trans.addToBackStack(null)
            trans.replace(R.id.control_frag_layout,fragment)
            trans.commit()

        }



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

        val userSettings = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val prefEditor = userSettings.edit()

        if(view.id == R.id.control_top_btn_auto){
            control_top_btn_auto.setTextColor(Color.WHITE)
            control_top_btn_auto.setBackgroundResource(R.color.vf_lightblue)

            control_top_btn_manual.setTextColor(Color.BLACK)
            control_top_btn_manual.setBackgroundColor(Color.TRANSPARENT)


            val bundle = Bundle()
            bundle.putString("ID", mac_id)
            val trans = supportFragmentManager.beginTransaction()
            val fragment = Control_aFragment()
            fragment.arguments = bundle
            trans.addToBackStack(null)
            trans.replace(R.id.control_frag_layout,fragment)
            trans.commit()

            val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id/ControlMode")
            db.child("acid_mode").setValue(1)

            prefEditor.putString("a_mode", "1")
            prefEditor.apply()

            Toast.makeText(this,"Switch To Auto Mode", Toast.LENGTH_SHORT).show()

        }else if (view.id == R.id.control_top_btn_manual){

            control_top_btn_manual.setTextColor(Color.WHITE)
            control_top_btn_manual.setBackgroundResource(R.color.vf_lightblue)

            control_top_btn_auto.setTextColor(Color.BLACK)
            control_top_btn_auto.setBackgroundColor(Color.TRANSPARENT)


            val bundle = Bundle()
            bundle.putString("ID", mac_id)
            val trans = supportFragmentManager.beginTransaction()
            val fragment = Control_mFragment()
            fragment.arguments = bundle
            trans.addToBackStack(null)
            trans.replace(R.id.control_frag_layout,fragment)
            trans.commit()

            val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id/ControlMode")
            db.child("acid_mode").setValue(0)

            prefEditor.putString("a_mode", "0")
            prefEditor.apply()

            Toast.makeText(this,"Switch To Manual Mode",Toast.LENGTH_SHORT).show()
        }


    }

    fun backTomain(){
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }
}