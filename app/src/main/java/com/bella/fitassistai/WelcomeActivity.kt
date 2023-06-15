package com.bella.fitassistai

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.bella.fitassistai.databinding.ActivityWelcomeBinding
import com.bella.fitassistai.main.MainActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        setAction()
        setAnimation()
    }

    private fun setAction(){
        binding.btnStarted.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
        }
    }

    private fun setView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setAnimation(){
        val start = ObjectAnimator.ofFloat(binding.btnStarted, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(start)
            start()
        }
    }
}