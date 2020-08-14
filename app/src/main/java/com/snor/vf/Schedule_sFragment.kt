package com.snor.vf

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.database.FirebaseDatabase
import com.michaldrabik.classicmaterialtimepicker.CmtpDialogFragment
import com.michaldrabik.classicmaterialtimepicker.utilities.setOnTime24PickedListener

import kotlinx.android.synthetic.main.fragment_schedule_s.*


class Schedule_sFragment : Fragment() {
    var mac_id : String? = ""

    var pickfrom : String? = ""
    var picktill : String? = ""

    override fun onStart() {
        super.onStart()
        initValue()

        mac_id = arguments?.getString("ID")



        schedule_s_btn_from.setOnClickListener { btn_timefrom() }
        schedule_s_btn_till.setOnClickListener { btn_timetill() }
        schedule_s_btn_set.setOnClickListener { update() }


    }


    fun initValue(){
        val userSettings = this.getActivity()?.getSharedPreferences("Preferences", Context.MODE_PRIVATE)

        val s_lf = userSettings?.getString("s_lf","").toString()
        val s_lt = userSettings?.getString("s_lt","").toString()
        val s_pd = userSettings?.getString("s_pd","").toString().toInt()
        val r_hr = userSettings?.getString("r_hr","").toString().toInt()

        val firsttwo : String = s_lf.substring(0,2)
        val lasttwo : String = s_lf.substring(s_lf.length-2)
        pickfrom = firsttwo + ":" + lasttwo

        val sfirsttwo : String = s_lt.substring(0,2)
        val slasttwo : String = s_lt.substring(s_lf.length-2)
        picktill = sfirsttwo + ":" + slasttwo

        schedule_s_btn_from.text = pickfrom
        schedule_s_btn_till.text = picktill


        schedule_s_txt_mins.setText(s_pd.toString())
        schedule_s_txt_hpercent.setText(r_hr.toString())




    }


    fun btn_timefrom(){

        val tmp1 : String = pickfrom!!.substring(0,2)
        val tmp2 : String = pickfrom!!.substring(pickfrom!!.length-2)
        val hh = tmp1.toInt()
        val mm = tmp2.toInt()


        val dialog = CmtpDialogFragment.newInstance()
        dialog.setInitialTime24(hh, mm)
        dialog.setOnTime24PickedListener {
            //Toast.makeText(view!!.context, it.toString(), Toast.LENGTH_SHORT).show()
            schedule_s_btn_from.setText(it.toString())
            pickfrom = it.toString()
        }
     dialog.show(childFragmentManager, "TimePicker")

    }

    fun btn_timetill(){

        val tmp1 : String = picktill!!.substring(0,2)
        val tmp2 : String = picktill!!.substring(picktill!!.length-2)
        val hh = tmp1.toInt()
        val mm = tmp2.toInt()

        val dialog = CmtpDialogFragment.newInstance()
        dialog.setInitialTime24(hh, mm)
        dialog.setOnTime24PickedListener {
            //Toast.makeText(view!!.context, it.toString(), Toast.LENGTH_SHORT).show()
            schedule_s_btn_till.setText(it.toString())
            picktill = it.toString()
        }
        dialog.show(childFragmentManager, "TimePicker")

    }

    fun update(){

        var error : Boolean = false

        val p_min = schedule_s_txt_mins.text.toString().toInt()
        val r_hr = schedule_s_txt_hpercent.text.toString().toInt()

        if(picktill!!.compareTo(pickfrom!!) <0){
            schedule_s_err1.visibility = View.VISIBLE
            error = true
        }

        if (p_min < 0 || p_min > 60){
            schedule_s_err2.visibility = View.VISIBLE
            error = true
        }

        if (r_hr < 0 || r_hr > 100){
            schedule_s_err3.visibility = View.VISIBLE
            error = true
        }


        if (error == false){

            val from : String = pickfrom!!.substring(0,2) + pickfrom!!.substring(pickfrom!!.length-2)
            val till : String = picktill!!.substring(0,2) + picktill!!.substring(picktill!!.length-2)

            val db = FirebaseDatabase.getInstance().getReference("OTHER/$mac_id")
            db.child("Schedule/light_from").setValue(from)
            db.child("Schedule/light_to").setValue(till)
            db.child("Schedule/pump_duration").setValue(p_min)
            db.child("Range/humidity_range").setValue(r_hr)




            Toast.makeText(view!!.context,"Schedule Updated",Toast.LENGTH_SHORT).show()
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
        return inflater.inflate(R.layout.fragment_schedule_s, container, false)
    }

}





