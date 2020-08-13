package com.snor.vf

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.snor.vf.Class.Machine
import com.snor.vf.Class.VFController
import com.snor.vf.Control.IOTFunction
import com.snor.vf.Control.Setup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rv_machine.view.*
import kotlinx.android.synthetic.main.rv_newmachine.view.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {

    val controller = IOTFunction()

    var countMachine : Int = 1
    val list = arrayListOf<Machine>()

    override fun onStart() {
        super.onStart()
        initrv()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





    }




    fun initrv(){
        val adapter = GroupAdapter<GroupieViewHolder>()
        val rc = findViewById<RecyclerView>(R.id.main_rv_machine)
        OverScrollDecoratorHelper.setUpOverScroll(rc, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        val ref = FirebaseDatabase.getInstance().getReference("MT")
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                countMachine =+ snapshot.children.count()
                snapshot.children.forEach {
                    val mac = it.getValue(Machine::class.java)
                    if(mac != null){
                        list.add((mac))
                        if(list.count() == snapshot.children.count()){
                            list.sortBy { it.date_created }
                            list.forEach {
                                adapter.add(bindMachine(it))
                            }

                        }
                    }
                }
                adapter.add(bindNewMachine())
            }
        })



        rc.adapter = adapter

        Log.d("test", countMachine.toString())

    }


    class bindNewMachine() : Item<GroupieViewHolder>(){
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.itemView.rv_newmachine_layout.setOnClickListener {
                val i = Intent(it.context, NewMacActivity::class.java)
                it.context.startActivity(i)
                (it.context as Activity).finish()

            }
        }

        override fun getLayout(): Int {
            return  R.layout.rv_newmachine
        }
    }

    class bindMachine(val mac : Machine): Item<GroupieViewHolder>(){
        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            viewHolder.itemView.rv_machine_name.text = mac.machine_name
            viewHolder.itemView.rv_machine_layout.setOnClickListener {
                val i = Intent(it.context, LoadingActivity::class.java)
                i.putExtra(KEY, mac.machine_id)
                it.context.startActivity(i)
                (it.context as Activity).finish()
            }
        }

        override fun getLayout(): Int {
            return  R.layout.rv_machine
        }
    }

    companion object{
        val KEY = ""
    }







}