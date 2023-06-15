package com.bella.fitassistai.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bella.fitassistai.R
import com.bella.fitassistai.databinding.FragmentHomeBinding
import com.bella.fitassistai.detail.camera.CameraActivity
import com.bella.fitassistai.workout.dumbbell.DumbbellActivity
import com.bella.fitassistai.workout.pushup.PushupActivity
import com.bella.fitassistai.workout.squat.SquatActivity

class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val buttonDumbbell = view.findViewById<Button>(R.id.btn_dumbbell)
        buttonDumbbell.setOnClickListener {
            val activity = requireActivity()
            activity.startActivity(Intent(activity, DumbbellActivity::class.java))
        }
        val buttonPushup = view.findViewById<Button>(R.id.btn_pushup)
        buttonPushup.setOnClickListener {
            val activity = requireActivity()
            activity.startActivity(Intent(activity, PushupActivity::class.java))
        }
        val buttonSquat = view.findViewById<Button>(R.id.btn_squat)
        buttonSquat.setOnClickListener {
            val activity = requireActivity()
            activity.startActivity(Intent(activity, SquatActivity::class.java))
        }

        return view
    }
}