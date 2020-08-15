package com.snor.vf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_control_m.*


class Control_mFragment : Fragment() {

    var mac_id : String? = ""

    override fun onStart() {
        super.onStart()

        mac_id = arguments?.getString("ID")

        control_m_btnphup.setOnClickListener { acidup() }
        control_m_btnphdown.setOnClickListener { aciddown() }
        control_m_btnnutA.setOnClickListener { nutrienda() }
        control_m_btnnutB.setOnClickListener { nutriendb() }

    }

    fun acidup(){

        val ml = control_m_txtphup.text.toString().toInt()

        if(ml > 0){
            val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id")
            db.child("Control/acid_up").setValue(1)
            db.child("Control/acid_up_ml").setValue(ml)

            control_m_txtphup.setText("1")

            Toast.makeText(view!!.context,"Executing", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(view!!.context,"Must Greater Than 0", Toast.LENGTH_SHORT).show()
        }



    }

    fun aciddown(){

        val ml = control_m_txtphdown.text.toString().toInt()

        if(ml > 0){
            val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id")
            db.child("Control/acid_down").setValue(1)
            db.child("Control/acid_down_ml").setValue(ml)

            control_m_txtphdown.setText("1")

            Toast.makeText(view!!.context,"Executing", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(view!!.context,"Must Greater Than 0", Toast.LENGTH_SHORT).show()
        }
    }

    fun nutrienda(){

        val ml = control_m_txtnutA.text.toString().toInt()
        if(ml > 0){
            val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id")
            db.child("Control/nutrient_a").setValue(1)
            db.child("Control/nutrient_a_ml").setValue(ml)

            control_m_txtnutA.setText("1")

            Toast.makeText(view!!.context,"Executing", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(view!!.context,"Must Greater Than 0", Toast.LENGTH_SHORT).show()
        }

    }

    fun nutriendb(){

        val ml = control_m_txtnutB.text.toString().toInt()
        if(ml > 0){
            val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id")
            db.child("Control/nutrient_b").setValue(1)
            db.child("Control/nutrient_b_ml").setValue(ml)

            control_m_txtnutB.setText("1")

             Toast.makeText(view!!.context,"Executing", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(view!!.context,"Must Greater Than 0", Toast.LENGTH_SHORT).show()
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
        return inflater.inflate(R.layout.fragment_control_m, container, false)
    }


}