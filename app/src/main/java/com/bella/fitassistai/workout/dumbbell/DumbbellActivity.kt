package com.bella.fitassistai.workout.dumbbell

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bella.fitassistai.databinding.ActivityDumbbellBinding
import com.bella.fitassistai.detail.camera.CameraActivity

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