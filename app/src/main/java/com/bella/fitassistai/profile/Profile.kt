package com.bella.fitassistai.profile

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bella.fitassistai.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class Profile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

    private fun userInfo(){
        val userRef = FirebaseDatabase.getInstance().getReference().child("")
    }

}