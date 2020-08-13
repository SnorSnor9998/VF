package com.snor.vf

import android.content.Context
import android.content.Intent
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

class LoadingActivity : AppCompatActivity() {

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
                        Log.d("Status", "DONE CATCHING" + mac.machine_id)

                    }
                }
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