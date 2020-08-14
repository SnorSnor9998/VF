package com.snor.vf

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_schedule_m.*


class Schedule_mFragment : Fragment() {

    var mac_id : String? = ""
    var light : Boolean? = null
    var pump : Boolean? = null
    var fan : Boolean? = null


    override fun onStart() {
        super.onStart()

        mac_id = arguments?.getString("ID")
        initValue()

        schedule_m_btn_light.setOnClickListener {
            if (light == true){
                lightoff()
            }else if(light == false){
                lighton()
            }
        }

        schedule_m_btn_pump.setOnClickListener {
            if (pump == true){
                pumpoff()
            }else if (pump == false){
                pumpon()
            }
        }

        schedule_m_btn_fan.setOnClickListener {
            if(fan == true){
                fanoff()
            }else if(fan == false){
                fanon()
            }
        }


    }



    fun initValue(){

        val userSettings = this.getActivity()?.getSharedPreferences("Preferences", Context.MODE_PRIVATE)

        val cm_p = userSettings?.getString("cm_p","").toString().toInt()
        val cm_f = userSettings?.getString("cm_f","").toString().toInt()
        val cm_l = userSettings?.getString("cm_l","").toString().toInt()



        if (cm_p == 1){
            schedule_m_btn_pump.setImageResource(R.drawable.icons8_pump_64px_b)
            schedule_m_lbl_pump.setText("PUMP ON")
            pump = true
        }else if (cm_p==0){
            schedule_m_btn_pump.setImageResource(R.drawable.icons8_pump_64px_g)
            schedule_m_lbl_pump.setText("PUMP OFF")
            pump = false
        }

        if(cm_f == 1){
            schedule_m_btn_fan.setImageResource(R.drawable.icons8_fan_64px_lb)
            schedule_m_lbl_fan.setText("FAN ON")
            fan = true
        }else if(cm_f == 0){
            schedule_m_btn_fan.setImageResource(R.drawable.icons8_fan_64px_g)
            schedule_m_lbl_fan.setText("FAN OFF")
            fan = false
        }

        if(cm_l == 1){
            schedule_m_btn_light.setImageResource(R.drawable.icons8_light_64px_b)
            schedule_m_lbl_light.setText("LIGHT ON")
            light = true
        }else if (cm_l == 0){
            schedule_m_btn_light.setImageResource(R.drawable.icons8_light_64px_g)
            schedule_m_lbl_light.setText("LIGHT OFF")
            light = false
        }


    }

    fun fanoff(){
        schedule_m_btn_fan.setImageResource(R.drawable.icons8_fan_64px_g)
        schedule_m_lbl_fan.setText("FAN OFF")
        fan = false

        val userSettings = this.getActivity()?.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val prefEditor = userSettings?.edit()
        prefEditor?.putString("cm_f", "0")
        prefEditor?.apply()

        val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id/Control")
        db.child("fan").setValue(0)
    }

    fun fanon(){
        schedule_m_btn_fan.setImageResource(R.drawable.icons8_fan_64px_lb)
        schedule_m_lbl_fan.setText("FAN ON")
        fan = true

        val userSettings = this.getActivity()?.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val prefEditor = userSettings?.edit()
        prefEditor?.putString("cm_f", "1")
        prefEditor?.apply()

        val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id/Control")
        db.child("fan").setValue(1)
    }

    fun pumpoff(){
        schedule_m_btn_pump.setImageResource(R.drawable.icons8_pump_64px_g)
        schedule_m_lbl_pump.setText("PUMP OFF")
        pump = false

        val userSettings = this.getActivity()?.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val prefEditor = userSettings?.edit()
        prefEditor?.putString("cm_p", "0")
        prefEditor?.apply()

        val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id/Control")
        db.child("pump").setValue(0)
    }

    fun pumpon(){
        schedule_m_btn_pump.setImageResource(R.drawable.icons8_pump_64px_b)
        schedule_m_lbl_pump.setText("PUMP ON")
        pump = true

        val userSettings = this.getActivity()?.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val prefEditor = userSettings?.edit()
        prefEditor?.putString("cm_p", "1")
        prefEditor?.apply()

        val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id/Control")
        db.child("pump").setValue(1)

    }

    fun lightoff(){
        schedule_m_btn_light.setImageResource(R.drawable.icons8_light_64px_g)
        schedule_m_lbl_light.setText("LIGHT OFF")
        light = false

        val userSettings = this.getActivity()?.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val prefEditor = userSettings?.edit()

        prefEditor?.putString("cm_l", "0")
        prefEditor?.apply()

        val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id/Control")
        db.child("light").setValue(0)
    }

    fun lighton(){
        schedule_m_btn_light.setImageResource(R.drawable.icons8_light_64px_b)
        schedule_m_lbl_light.setText("LIGHT ON")
        light = true


        val userSettings = this.getActivity()?.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val prefEditor = userSettings?.edit()
        prefEditor?.putString("cm_l", "1")
        prefEditor?.apply()

        val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id/Control")
        db.child("light").setValue(1)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_m, container, false)
    }

}