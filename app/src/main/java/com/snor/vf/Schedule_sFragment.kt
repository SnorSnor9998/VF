package com.snor.vf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.michaldrabik.classicmaterialtimepicker.CmtpDialogFragment
import com.michaldrabik.classicmaterialtimepicker.utilities.setOnTime24PickedListener

import kotlinx.android.synthetic.main.fragment_schedule_s.*


class Schedule_sFragment : Fragment() {


    override fun onStart() {
        super.onStart()

        schedule_s_btn_from.setOnClickListener { btn_timefrom() }
        schedule_s_btn_till.setOnClickListener { btn_timetill() }
        schedule_s_btn_set.setOnClickListener { update() }


    }


    fun btn_timefrom(){
        val dialog = CmtpDialogFragment.newInstance()
        dialog.setInitialTime24(9, 0)
        dialog.setOnTime24PickedListener {
            //Toast.makeText(view!!.context, it.toString(), Toast.LENGTH_SHORT).show()
            schedule_s_btn_from.setText(it.toString())
        }
     dialog.show(childFragmentManager, "TimePicker")

    }

    fun btn_timetill(){
        val dialog = CmtpDialogFragment.newInstance()
        dialog.setInitialTime24(21, 0)
        dialog.setOnTime24PickedListener {
            //Toast.makeText(view!!.context, it.toString(), Toast.LENGTH_SHORT).show()
            schedule_s_btn_till.setText(it.toString())
        }
        dialog.show(childFragmentManager, "TimePicker")

    }

    fun update(){}






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_s, container, false)
    }

}





