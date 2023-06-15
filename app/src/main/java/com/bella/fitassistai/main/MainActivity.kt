package com.bella.fitassistai.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.bella.fitassistai.R
import com.bella.fitassistai.WelcomeActivity
import com.bella.fitassistai.databinding.ActivityMainBinding
import com.bella.fitassistai.detail.History
import com.bella.fitassistai.detail.Home
import com.bella.fitassistai.detail.login.LoginActivity
import com.bella.fitassistai.detail.register.RegisterActivity
import com.bella.fitassistai.profile.Profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Home -> replaceFragment(Home())
                R.id.History -> replaceFragment(History())
                R.id.Profile -> replaceFragment(Profile())

                else -> {}
            }
            true
        }

        auth = Firebase.auth
        val firebaseUser = auth.currentUser
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment,fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_menu, menu)
        return true
    }
}