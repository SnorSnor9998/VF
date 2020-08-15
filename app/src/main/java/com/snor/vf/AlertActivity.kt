package com.snor.vf

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_alert.*

class AlertActivity : AppCompatActivity() {
    var mac_id : String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert)

        initValue()
        alert_btn_back.setOnClickListener { backTomain() }

        alert_btn_update.setOnClickListener { update() }

    }

    fun nav_click(view: View) {


        if (view.id == R.id.alert_nv1) {
            val i = Intent(this, DataActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()


        } else if (view.id == R.id.alert_nv2) {
            val i = Intent(this, ScheduleActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()

        } else if (view.id == R.id.alert_nv3) {
            val i = Intent(this, CameraActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()



        } else if (view.id == R.id.alert_nv4) {
            val i = Intent(this, ControlActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()


        } else if (view.id == R.id.alert_nv5) {


        }


    }

    fun backTomain(){
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }

    fun initValue(){
        val userSettings = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        mac_id = userSettings.getString("Mac_id","").toString()
        alert_top_macname.text = userSettings.getString("Mac_name","").toString()


        val noti_aa = userSettings.getString("noti_aa","").toString()
        val noti_ab = userSettings.getString("noti_ab","").toString()
        val noti_ae = userSettings.getString("noti_ae","").toString().toBoolean()
        val noti_hu = userSettings.getString("noti_hu","").toString()
        val noti_hue = userSettings.getString("noti_hue","").toString().toBoolean()
        val noti_tempa = userSettings.getString("noti_tempa","").toString()
        val noti_tempb = userSettings.getString("noti_tempb","").toString()
        val noti_tempe = userSettings.getString("noti_tempe","").toString().toBoolean()


        if(noti_ae == true){
            alert_cb_phenable.isChecked = true
        }else if (noti_ae == false){
            alert_cb_phenable.isChecked = false
        }

        if(noti_hue == true){
            alert_cb_hu.isChecked = true
        }else if(noti_hue == false){
            alert_cb_hu.isChecked = false
        }

        if(noti_tempe == true){
            alert_cb_tempenable.isChecked = true
        }else if(noti_tempe == false){
            alert_cb_tempenable.isChecked = false
        }

        alert_txt_phfrom.setText(noti_ab)
        alert_txt_phtill.setText(noti_aa)

        alert_txt_tempfrom.setText(noti_tempb)
        alert_txt_temptill.setText(noti_tempa)

        alert_txt_ghu.setText(noti_hu)




    }

    fun update(){

        var error : Boolean = false

        val noti_aa = alert_txt_phtill.text.toString().toFloat()
        val noti_ab = alert_txt_phfrom.text.toString().toFloat()
        val noti_ae = alert_cb_phenable.isChecked
        val noti_hu = alert_txt_ghu.text.toString().toFloat()
        val noti_hue = alert_cb_hu.isChecked
        val noti_tempa = alert_txt_temptill.text.toString().toFloat()
        val noti_tempb = alert_txt_tempfrom.text.toString().toFloat()
        val noti_tempe = alert_cb_tempenable.isChecked

        val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id")
        db.child("Notification/acid_above").setValue(noti_aa)
        db.child("Notification/acid_below").setValue(noti_ab)
        db.child("Notification/acid_enable").setValue(noti_ae)
        db.child("Notification/humidity").setValue(noti_hu)
        db.child("Notification/humidity_enable").setValue(noti_hue)
        db.child("Notification/temp_above").setValue(noti_tempa)
        db.child("Notification/temp_below").setValue(noti_tempb)
        db.child("Notification/temp_enable").setValue(noti_tempe)


        //set global variable
        val userSettings = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val prefEditor = userSettings.edit()
        prefEditor.putString("noti_aa", noti_aa.toString())
        prefEditor.putString("noti_ab", noti_ab.toString())
        prefEditor.putString("noti_ae", noti_ae.toString())
        prefEditor.putString("noti_hu", noti_hu.toString())
        prefEditor.putString("noti_hue", noti_hue.toString())
        prefEditor.putString("noti_tempa", noti_tempa.toString())
        prefEditor.putString("noti_tempb", noti_tempb.toString())
        prefEditor.putString("noti_tempe", noti_tempe.toString())


        prefEditor.apply()

        Toast.makeText(this,"Updated", Toast.LENGTH_SHORT).show()


    }

}