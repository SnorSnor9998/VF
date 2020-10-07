package com.snor.vf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.fragment_data__c.*


class Data_CFragment : Fragment() {




    override fun onStart() {
        super.onStart()

        val lineDataSet = LineDataSet(dataVlues(), "Data Set")
        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(lineDataSet)

        val data = LineData(dataSets)
        linechart1.setData(data)
        linechart1.invalidate()

        val desc = Description()
        desc.text = "pH"
        
        linechart1.description = desc




    }

    private fun dataVlues(): ArrayList<Entry>? {
        val dataVal = ArrayList<Entry>()
        dataVal.add(Entry(0f, 20f))
        dataVal.add(Entry(1f, 24f))
        dataVal.add(Entry(2f, 2f))
        dataVal.add(Entry(3f, 10f))
        dataVal.add(Entry(4f, 28f))
        dataVal.add(Entry(5f, 20f))
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