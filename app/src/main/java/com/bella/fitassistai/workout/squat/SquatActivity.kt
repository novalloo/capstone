package com.bella.fitassistai.workout.squat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bella.fitassistai.databinding.ActivitySquatctivityBinding
import com.bella.fitassistai.detail.camera.CameraActivity

class SquatActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySquatctivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySquatctivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Squat"

        setAction()

    }
    private fun setAction(){
        binding.btnStarted.setOnClickListener {
            startActivity(Intent(this@SquatActivity, CameraActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}