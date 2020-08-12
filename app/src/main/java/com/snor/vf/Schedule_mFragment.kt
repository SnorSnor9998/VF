package com.snor.vf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_schedule_m.*


class Schedule_mFragment : Fragment() {

    var light : Boolean? = null
    var pump : Boolean? = null
    var fan : Boolean? = null

    override fun onStart() {
        super.onStart()



        light = false
        schedule_m_btn_light.setOnClickListener {

            if (light == true){
                schedule_m_btn_light.setImageResource(R.drawable.icons8_light_64px_g)
                schedule_m_lbl_light.setText("LIGHT OFF")
                light = false
            }else if(light == false){
                schedule_m_btn_light.setImageResource(R.drawable.icons8_light_64px_b)
                schedule_m_lbl_light.setText("LIGHT ON")
                light = true
            }




        }

        pump = false
        schedule_m_btn_pump.setOnClickListener {

            if (pump == true){
                schedule_m_btn_pump.setImageResource(R.drawable.icons8_pump_64px_g)
                schedule_m_lbl_pump.setText("PUMP OFF")
                pump = false
            }else if (pump == false){
                schedule_m_btn_pump.setImageResource(R.drawable.icons8_pump_64px_b)
                schedule_m_lbl_pump.setText("PUMP ON")
                pump = true
            }



        }

        fan = false
        schedule_m_btn_fan.setOnClickListener {

            if(fan == true){
                schedule_m_btn_fan.setImageResource(R.drawable.icons8_fan_64px_g)
                schedule_m_lbl_fan.setText("FAN OFF")
                fan = false
            }else if(fan == false){
                schedule_m_btn_fan.setImageResource(R.drawable.icons8_fan_64px_lb)
                schedule_m_lbl_fan.setText("FAN ON")
                fan = true
            }

        }





    }

    fun updateLightOperation(){}

    fun updatePumpOperation(){}

    fun updateFanOperation(){}





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