package com.bella.fitassistai.detail.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.util.Log
import android.view.SurfaceView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bella.fitassistai.R
import com.bella.fitassistai.data.Device
import com.bella.fitassistai.databinding.ActivityCameraBinding
import com.bella.fitassistai.movenet.ModelType
import com.bella.fitassistai.movenet.MoveNet
import com.bella.fitassistai.workout.WorkoutCounter
import com.bella.fitassistai.workout.pushup.PushupCounter
import com.bella.fitassistai.workout.squat.SquatCounter
import kotlinx.coroutines.launch
import com.bella.fitassistai.data.Person

public class CameraActivity : AppCompatActivity() {

    companion object {
        var personForCount : MutableList<Person> = mutableListOf()

        fun setWorkoutCounter(workout : String)
        {
            Log.d("setWorkoutCounter",workout)
            if (workout == "Squat") {
                workoutCounter = SquatCounter()
            }

            else if (workout == "Pushup") {
                workoutCounter = PushupCounter()
            }
        }
        var workoutCounter : WorkoutCounter = SquatCounter()
    }

    override fun getApplicationContext(): Context {
        return super.getApplicationContext()
    }
    private var cameraSource: CameraSource? = null
    private lateinit var surfaceView: SurfaceView
    private var device = Device.GPU
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {

                openCamera()
            } else {
            }
        }

    private var modelPos = 1

    private fun openCamera() {
        if (isCameraPermissionGranted()) {
            if (cameraSource == null) {
                cameraSource =
                    CameraSource(surfaceView, object : CameraSource.CameraSourceListener {
                        override fun onFPSListener(fps: Int) {
                        }

                        override fun onDetectedInfo(
                            personScore: Float?,
                            poseLabels: List<Pair<String, Float>>?
                        ) {
                        }
                    },applicationContext).apply {
                        prepareCamera()
                    }

                val visualizeCoroutine = lifecycleScope.launch {
                    cameraSource?.initCamera()
                }
            }
            createPoseEstimator()
        }

        Log.i("openCamera End","openCamera End")
    }

    private fun isCameraPermissionGranted(): Boolean {
        Log.i("CameraActivity","isCameraPermissionGranted")
        return checkPermission(
            Manifest.permission.CAMERA,
            Process.myPid(),
            Process.myUid()
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        Log.i("CameraActivity : ","requestPermission")
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) -> {
                openCamera()
            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA
                )
            }
        }
    }

    private fun createPoseEstimator() {
        Log.i("CameraActivity","createPoseEstimator")

        val poseDetector = MoveNet.create(this, device, ModelType.Thunder)
        poseDetector?.let { detector ->
            cameraSource?.setDetector(detector)
        }
    }

    lateinit var binding : ActivityCameraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCameraBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_camera)

        surfaceView = findViewById(R.id.surfaceView)

        if (!isCameraPermissionGranted()) {
            requestPermission()
        }
    }

    override fun onStart() {
        super.onStart()
        openCamera()
    }

    override fun onResume() {
        cameraSource?.resume()
        super.onResume()
    }

    override fun onPause() {
        cameraSource?.close()
        cameraSource = null
        super.onPause()
    }

    override fun onBackPressed() {
    }
}