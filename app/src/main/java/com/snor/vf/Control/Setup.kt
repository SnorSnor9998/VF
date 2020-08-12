package com.snor.vf.Control

import com.google.firebase.database.FirebaseDatabase
import com.snor.vf.Class.VFController

class Setup {

    fun createControlClass(){
        val c = VFController()
        val db = FirebaseDatabase.getInstance().getReference("ControlPanel")
        db.setValue(c)
    }



}