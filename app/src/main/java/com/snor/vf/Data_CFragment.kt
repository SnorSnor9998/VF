package com.snor.vf

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.snor.vf.Class.DataV2
import kotlinx.android.synthetic.main.fragment_data__c.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class Data_CFragment : Fragment() {

    var data = arrayListOf<DataV2>()
    private val mHandler: Handler = Handler()


    override fun onStart() {
        super.onStart()

        try {
            mToastRunnable.run()
        }catch (e :Exception){
            Toast.makeText(this.context,e.toString(),Toast.LENGTH_SHORT).show()
        }

    }

    override fun onPause() {
        super.onPause()

        try {
            mHandler.removeCallbacks(mToastRunnable)
        }catch (e :Exception){
            Toast.makeText(this.context,e.toString(),Toast.LENGTH_SHORT).show()
        }

    }



    private val mToastRunnable: Runnable = object : Runnable {
        override fun run() {
            getData()

            Handler().postDelayed(
                {
                    display()
                }, 5000)

            mHandler.postDelayed(this, 10000)
        }
    }






    private fun getData(){
        try{
            val list = arrayListOf<DataV2>()
            val ref = FirebaseDatabase.getInstance().getReference("OTHER/MAC0/Data").limitToLast(20)
            ref.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {}
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        val d = it.getValue(DataV2::class.java)
                        if (d != null){
                            list.add(d)

                            if(list.count() == snapshot.childrenCount.toInt()){
                                list.sortBy { it.time }
                                data = list

                            }
                        }
                    }
                }
            })
        }catch (e :Exception){

        }


    }

    private fun display(){

        try {
            //temp
            val lineDataSet1 = LineDataSet( tempdataValues() , "Temperature")

            val dataSets1: ArrayList<ILineDataSet> = ArrayList()
            dataSets1.add(lineDataSet1)
            val data1 = LineData(dataSets1)

            linechart1.setData(data1)
            linechart1.invalidate()

            val desc1 = Description()
            desc1.text = "Room Temperature"
            linechart1.description = desc1

            ////hu
            val lineDataSet2 = LineDataSet( hudataValues() , "Humidity")

            val dataSets2: ArrayList<ILineDataSet> = ArrayList()
            dataSets2.add(lineDataSet2)
            val data2 = LineData(dataSets2)

            linechart2.setData(data2)
            linechart2.invalidate()

            val desc2 = Description()
            desc2.text = "Room Humidity"
            linechart2.description = desc2


            //ph
            val lineDataSet3 = LineDataSet( phdataValues() , "ph")

            val dataSets3: ArrayList<ILineDataSet> = ArrayList()
            dataSets3.add(lineDataSet3)
            val data3 = LineData(dataSets3)

            linechart3.setData(data3)
            linechart3.invalidate()

            val desc3 = Description()
            desc3.text = "Water pH Level"
            linechart3.description = desc3
        }catch (e :Exception){

        }




    }

    private fun tempdataValues(): ArrayList<Entry>? {
        val dataVal = ArrayList<Entry>()

        for(i in 0 until data.count() step 1){
            dataVal.add(Entry(i.toFloat(), data[i].temp))

        }
        return dataVal
    }

    private fun hudataValues(): ArrayList<Entry>? {
        val dataVal = ArrayList<Entry>()

        for(i in 0 until data.count() step 1){
            dataVal.add(Entry(i.toFloat(), data[i].hu))

        }
        return dataVal
    }

    private fun phdataValues(): ArrayList<Entry>? {
        val dataVal = ArrayList<Entry>()

        for(i in 0 until data.count() step 1){
            dataVal.add(Entry(i.toFloat(), data[i].ph))

        }
        return dataVal
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data__c, container, false)
    }


}