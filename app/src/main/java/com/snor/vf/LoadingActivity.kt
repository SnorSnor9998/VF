package com.snor.vf

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.snor.vf.Class.Machine
import kotlinx.android.synthetic.main.activity_new_mac.*
import kotlinx.android.synthetic.main.activity_schedule.*

class  LoadingActivity : AppCompatActivity() {

    var macid : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        macid = intent.getStringExtra(MainActivity.KEY)
        if (macid == null){
            macid = intent.getStringExtra(NewMacActivity.KEY)
        }

        mToastRunnable.run()
        readMAC()

        Handler().postDelayed(
            {
                val i = Intent(this, DataActivity::class.java)
                startActivity(i)
                finish()
            }, 3000)

    }


    fun readMAC(){

        //set global variable
        val userSettings = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val prefEditor = userSettings.edit()

        //read machine
        val ref = FirebaseDatabase.getInstance().getReference("/MT").orderByChild("machine_id").equalTo(macid)
        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val mac = it.getValue(Machine::class.java)
                    if (mac != null){

                        prefEditor.putString("Mac_id",mac.machine_id)
                        prefEditor.putString("Mac_name",mac.machine_name)
                        prefEditor.apply()
                        Log.d("Status", "DONE CATCHING ID " + mac.machine_id)

                    }
                }
            }
        })

        //read control mode
        val ref2 = FirebaseDatabase.getInstance().getReference("OTHER/$macid")
        ref2.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {

                val f_mode = snapshot.child("ControlMode/fan_mode").value
                val l_mode = snapshot.child("ControlMode/light_mode").value
                val p_mode = snapshot.child("ControlMode/pump_mode").value
                val a_mode = snapshot.child("ControlMode/acid_mode").value

                prefEditor.putString("f_mode", f_mode.toString())
                prefEditor.putString("l_mode", l_mode.toString())
                prefEditor.putString("p_mode", p_mode.toString())
                prefEditor.putString("a_mode", a_mode.toString())
                prefEditor.apply()
                Log.d("Status", "DONE CATCHING FLP ")


                //read manual control
                val cm_p = snapshot.child("Control/pump").value
                val cm_f = snapshot.child("Control/fan").value
                val cm_l = snapshot.child("Control/light").value


                prefEditor.putString("cm_p",cm_p.toString())
                prefEditor.putString("cm_f",cm_f.toString())
                prefEditor.putString("cm_l",cm_l.toString())
                prefEditor.apply()
                Log.d("Status", "DONE CATCHING Manual Control ")


                //read range
                val r_ara = snapshot.child("Range/acid_rangeabove").value
                val r_arb = snapshot.child("Range/acid_rangebelow").value
                val r_hr = snapshot.child("Range/humidity_range").value

                prefEditor.putString("r_ara",r_ara.toString())
                prefEditor.putString("r_arb",r_arb.toString())
                prefEditor.putString("r_hr",r_hr.toString())
                prefEditor.apply()
                Log.d("Status", "DONE CATCHING Range ")

                //read schedule
                val s_lf = snapshot.child("Schedule/light_from").value
                val s_lt = snapshot.child("Schedule/light_to").value
                val s_pd = snapshot.child("Schedule/pump_duration").value

                prefEditor.putString("s_lf",s_lf.toString())
                prefEditor.putString("s_lt",s_lt.toString())
                prefEditor.putString("s_pd",s_pd.toString())
                prefEditor.apply()
                Log.d("Status", "DONE CATCHING Schedule ")


                //read notification
                val noti_aa = snapshot.child("Notification/acid_above").value
                val noti_ab = snapshot.child("Notification/acid_below").value
                val noti_ae = snapshot.child("Notification/acid_enable").value
                val noti_hu = snapshot.child("Notification/humidity").value
                val noti_hue = snapshot.child("Notification/humidity_enable").value
                val noti_tempa = snapshot.child("Notification/temp_above").value
                val noti_tempb = snapshot.child("Notification/temp_below").value
                val noti_tempe = snapshot.child("Notification/temp_enable").value

                prefEditor.putString("noti_aa", noti_aa.toString())
                prefEditor.putString("noti_ab", noti_ab.toString())
                prefEditor.putString("noti_ae", noti_ae.toString())
                prefEditor.putString("noti_hu", noti_hu.toString())
                prefEditor.putString("noti_hue", noti_hue.toString())
                prefEditor.putString("noti_tempa", noti_tempa.toString())
                prefEditor.putString("noti_tempb", noti_tempb.toString())
                prefEditor.putString("noti_tempe", noti_tempe.toString())


                prefEditor.apply()
                Log.d("Status", "DONE CATCHING Notification")








            }
        })





    }


    private val mHandler: Handler = Handler()
    var count = 0

    private val mToastRunnable: Runnable = object : Runnable {
        override fun run() {

            if (count == 0){
                loading_text.text = "Loading"
            }else if(count == 1){
                loading_text.text = "Loading."
            }else if(count == 2){
                loading_text.text = "Loading.."
            }else if(count == 3){
                loading_text.text = "Loading..."

            }

            if(count == 3){
                count = 0
            }else{
                count++
            }

            mHandler.postDelayed(this, 1000)
        }
    }
}