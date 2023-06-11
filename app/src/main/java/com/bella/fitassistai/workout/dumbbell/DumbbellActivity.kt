package com.bella.fitassistai.workout.dumbbell

import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bella.fitassistai.R
import com.bella.fitassistai.databinding.ActivityDumbbellBinding
import com.bella.fitassistai.databinding.ActivityPushupBinding
import com.bella.fitassistai.detail.camera.CameraActivity
import okio.IOException

class DumbbellActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDumbbellBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDumbbellBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnStarted.setOnClickListener {
            val pushUpIntent = Intent(this, CameraActivity::class.java)
            startActivity(pushUpIntent)
        }
    }
}