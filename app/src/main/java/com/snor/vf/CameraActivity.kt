package com.snor.vf

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()

        val url = "https://www.videvo.net/videvo_files/converted/2015_06/preview/vegetablepatch2_Videvo.mov94208.webm"
        val uri = Uri.parse(url)
        cam_video_view.setVideoURI(uri)

        playVideo()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        //back button
        camera_btn_back.setOnClickListener { backTomain() }
    }

    fun playVideo(){


        val mediaController : MediaController
        mediaController = MediaController(this)

        cam_video_view.setOnPreparedListener {
            mediaController.setAnchorView(cam_video_view)
            cam_video_view.setMediaController(mediaController)
            cam_video_view.start()
            it.isLooping = true

        }



    }

    fun nav_click(view: View) {
        if (view.id == R.id.camera_nv1) {
            val i = Intent(this, DataActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()


        } else if (view.id == R.id.camera_nv2) {
            val i = Intent(this, ScheduleActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()

        } else if (view.id == R.id.camera_nv3) {


        } else if (view.id == R.id.camera_nv4) {
            val i = Intent(this, ControlActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()


        } else if (view.id == R.id.camera_nv5) {
            val i = Intent(this, AlertActivity::class.java)
            startActivity(i)
            overridePendingTransition(0, 0)
            finish()

        }
    }

    fun backTomain(){
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }

}