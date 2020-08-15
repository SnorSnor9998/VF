package com.snor.vf

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_control_a.*


class Control_aFragment : Fragment() {

    var mac_id : String? = ""


    override fun onStart() {
        super.onStart()

        mac_id = arguments?.getString("ID")

        initValue()

        control_a_btn_set.setOnClickListener { update() }

    }


    fun initValue(){
        val userSettings = this.getActivity()?.getSharedPreferences("Preferences", Context.MODE_PRIVATE)

        val a_a = userSettings?.getString("r_ara","").toString()
        val a_b = userSettings?.getString("r_arb","").toString()

        control_a_txt_phfrom.setText(a_b)
        control_a_txt_phtill.setText(a_a)

    }


    fun update(){

        var error : Boolean = false

        val a_b = control_a_txt_phfrom.text.toString().toFloat()
        val a_a = control_a_txt_phtill.text.toString().toFloat()

        if (a_b >= a_a ){
            error = true
            control_a_err1.visibility = View.VISIBLE
        }

        if (error == false){
            val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id")
            db.child("Range/acid_rangeabove").setValue(a_a)
            db.child("Range/acid_rangebelow").setValue(a_b)

            val userSettings = this.getActivity()?.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
            val prefEditor = userSettings?.edit()
            prefEditor?.putString("r_ara", a_a.toString())
            prefEditor?.putString("r_arb", a_b.toString())
            prefEditor?.apply()


            Toast.makeText(view!!.context,"Updated", Toast.LENGTH_SHORT).show()


        }



    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_control_a, container, false)
    }

}