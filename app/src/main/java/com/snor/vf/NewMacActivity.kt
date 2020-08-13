package com.snor.vf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.snor.vf.Class.*
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.activity_new_mac.*
import java.util.*

class NewMacActivity : AppCompatActivity() {
    var count = 0
    var countMachine = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_mac)

        mToastRunnable.run()
        createNewMachine()
    }

    private val mHandler: Handler = Handler()


    private val mToastRunnable: Runnable = object : Runnable {
        override fun run() {

            if (count == 0){
                loading_text.text = "Creating"
            }else if(count == 1){
                loading_text.text = "Creating."
            }else if(count == 2){
                loading_text.text = "Creating.."
            }else if(count == 3){
                loading_text.text = "Creating..."

            }

            if(count == 3){
                count = 0
            }else{
                count++
            }

            mHandler.postDelayed(this, 1000)
        }
    }

    companion object{
        val KEY = ""
    }

    fun createNewMachine(){

        val ref = FirebaseDatabase.getInstance().getReference("MT")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                countMachine =+ snapshot.children.count()
                Log.d("Count", countMachine.toString())
            }
        })

        val mac = Machine()
        Handler().postDelayed(
            {

                    val calendar = Calendar.getInstance()
                    mac.date_created = calendar.time
                    mac.machine_id = "MAC"+ countMachine
                    mac.machine_name = "Machine "+countMachine
                    mac.status = "ONLINE"

            val db1 = FirebaseDatabase.getInstance().getReference("MT/${mac.machine_id}")
            db1.setValue(mac)


                Handler().postDelayed(
                    {
                        val db2 = FirebaseDatabase.getInstance().getReference("OTHER/${mac.machine_id}/Control")
                        val control = Control()
                        control.machine_id = mac.machine_id
                        db2.setValue(control)

                        val db3 = FirebaseDatabase.getInstance().getReference("OTHER/${mac.machine_id}/ControlMode")
                        val controlmode = Control_Mode()
                        controlmode.machine_id = mac.machine_id
                        db3.setValue(controlmode)

                        val db4 = FirebaseDatabase.getInstance().getReference("OTHER/${mac.machine_id}/Notification")
                        val noti = Notification()
                        noti.machine_id = mac.machine_id
                        db4.setValue(noti)

                        val db5 = FirebaseDatabase.getInstance().getReference("OTHER/${mac.machine_id}/Range")
                        val range = Range()
                        range.machine_id = mac.machine_id
                        db5.setValue(range)

                        val db6 = FirebaseDatabase.getInstance().getReference("OTHER/${mac.machine_id}/Schedule")
                        val sch = Schedule()
                        sch.machine_id = mac.machine_id
                        db6.setValue(sch)

                        Toast.makeText(this, "New Machine Created", Toast.LENGTH_SHORT).show()
                    }, 3000)

            }, 5000)


        Handler().postDelayed(
            {
                val i = Intent(this, LoadingActivity::class.java)
                i.putExtra(KEY, mac.machine_id)
                startActivity(i)
                finish()
            }, 10000)


    }


}