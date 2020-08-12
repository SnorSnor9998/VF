package com.snor.vf.Control

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class IOTFunction {

    val db = FirebaseDatabase.getInstance().reference

    fun relaylight(switch : Boolean){
        if (switch){
        db.child("ControlPanel").child("relay_light").setValue(1)
        }
        else if (!switch){
            db.child("ControlPanel").child("relay_light").setValue(0)
        }

    }


    fun relaypump(switch: Boolean){
        if (switch){
            db.child("ControlPanel").child("relay_pump").setValue(1)
        }
        else if (!switch){
            db.child("ControlPanel").child("relay_pump").setValue(0)
        }
    }


    fun relayfan(switch: Boolean){
        if (switch){
            db.child("ControlPanel").child("relay_fan").setValue(1)
        }
        else if (!switch){
            db.child("ControlPanel").child("relay_fan").setValue(0)
        }
    }

    fun buzzer(switch: Boolean){
        if (switch){
            db.child("ControlPanel").child("buzzer").setValue(1)
        }
        else if (!switch){
            db.child("ControlPanel").child("buzzer").setValue(0)
        }
    }


}