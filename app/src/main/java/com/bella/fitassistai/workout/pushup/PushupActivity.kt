package com.bella.fitassistai.workout.pushup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bella.fitassistai.databinding.ActivityPushupBinding
import com.bella.fitassistai.detail.camera.CameraActivity

class PushupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPushupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPushupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnStarted.setOnClickListener {
            val pushUpIntent = Intent(this, CameraActivity::class.java)
            startActivity(pushUpIntent)
        }
    }
}