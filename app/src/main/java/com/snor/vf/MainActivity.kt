package com.snor.vf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.snor.vf.Class.VFController
import com.snor.vf.Control.IOTFunction
import com.snor.vf.Control.Setup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val controller = IOTFunction()

    override fun onStart() {
        super.onStart()
        initValue()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        btn_light.setOnClickListener { lighfunction() }
        btn_pump.setOnClickListener { pumpfunction() }
        btn_fan.setOnClickListener {}

    }




    fun lighfunction(){
        if (btn_light.text.toString().equals("ON")){
            controller.relaylight(true)
            btn_light.setText("OFF")
        }else{
            controller.relaylight(false)
            btn_light.setText("ON")
        }



    }

    fun pumpfunction(){
        if (btn_pump.text.toString().equals("ON")){
            controller.relaypump(true)
            btn_pump.setText("OFF")
            imageView.setImageResource(R.drawable.icons8_water_64px_b)
        }else{
            controller.relaypump(false)
            btn_pump.setText("ON")
            imageView.setImageResource(R.drawable.icons8_water_64px_g)
        }

    }

    fun getpumpstatusv2() : Boolean{
        var check : Boolean = false
        val db = FirebaseDatabase.getInstance().reference
        db.child("ControlPanel").child("relay_pump").addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.getValue().toString().equals("1")) {
                    btn_pump.text = "OFF"
                    imageView.setImageResource(R.drawable.icons8_water_64px_b)
                    Log.d("Check", "Run u mother")
                }
            }
        } )
        return check
    }


    fun initValue(){

        val db = FirebaseDatabase.getInstance().getReference("ControlPanel")
        db.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("Snap",snapshot.getValue().toString())

                val ctmp = snapshot.getValue(VFController::class.java)


                    if (ctmp!=null){
                        if (ctmp.relay_pump == 1){
                            btn_pump.text = "OFF"
                            imageView.setImageResource(R.drawable.icons8_water_64px_b)
                        }

                        if(ctmp.relay_light == 1){
                            btn_light.text = "OFF"
                            imageView2.setImageResource(R.drawable.icons8_light_64px_y)
                        }
                    }

                }


        })

    }





}