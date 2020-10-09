package com.snor.vf

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.fragment_data__f.*


class Data_FFragment : Fragment() {

    var mac_id : String? = ""
    private val mHandler: Handler = Handler()

    override fun onStart() {
        super.onStart()

        mac_id = arguments?.getString("ID")

        mToastRunnable.run()
        //stop
        //mHandler.removeCallbacks(mToastRunnable)


    }

    private val mToastRunnable: Runnable = object : Runnable {
        override fun run() {
            getData()
            mHandler.postDelayed(this, 1000)
        }
    }


    fun getData(){

        val ref = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id")
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                ref.child("Temp_Data/hu")

                val hu = snapshot.child("Temp_Data/hu").value
                val temp = snapshot.child("Temp_Data/temp").value
                val ph = snapshot.child("Temp_Data/ph").value

                data_f_hu.text = hu.toString() + " %"
                data_f_ph.text = ph.toString()
                data_f_temp.text = temp.toString() + " °C"



            }
        })




    }

    override fun onPause() {
        super.onPause()
        mHandler.removeCallbacks(mToastRunnable)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data__f, container, false)
    }


}