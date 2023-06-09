package com.bella.fitassistai.workout.dumbbell

import android.content.Context
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bella.fitassistai.R
import okio.IOException

class DumbbellActivity : AppCompatActivity() {

    /*val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
    val cameraId = cameraManager.cameraIdList[0] // Assuming the first camera is the desired one*/
    private lateinit var cameraDevice: CameraDevice
    private lateinit var mediaRecorder: MediaRecorder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_squatctivity)
    }
}

    /* cameraManager.openCamera(cameraId, object : CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            cameraDevice = camera
            // Start the recording process
        }

        override fun onDisconnected(camera: CameraDevice) {
            cameraDevice.close()
        }

        override fun onError(camera: CameraDevice, error: Int) {
            cameraDevice.close()
        }
    }, null)

    private fun prepareMediaRecorder() {
        mediaRecorder = MediaRecorder()

        // Set the video source
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE)

        // Set the audio source if needed
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)

        // Set the output file path
        mediaRecorder.setOutputFile(getOutputFilePath())

        // Set the output format and encoding parameters
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)

        // Set the video size and frame rate
        mediaRecorder.setVideoSize(videoWidth, videoHeight)
        mediaRecorder.setVideoFrameRate(videoFrameRate)

        // Set other desired parameters

        try {
            mediaRecorder.prepare()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun startRecording() {
        val surface = mediaRecorder.surface

        val recordBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_RECORD)
        recordBuilder.addTarget(surface)

        val surfaces = listOf(surface, imageReader.surface)
        cameraDevice.createCaptureSession(surfaces, object : CameraCaptureSession.StateCallback() {
            override fun onConfigured(session: CameraCaptureSession) {
                val captureRequest = recordBuilder.build()
                session.setRepeatingRequest(captureRequest, null, null)

                mediaRecorder.start()
            }

            override fun onConfigureFailed(session: CameraCaptureSession) {
                // Handle configuration failure
            }
        }, null)
    }

    private fun stopRecording() {
        mediaRecorder.stop()
        mediaRecorder.reset()
        mediaRecorder.release()

        cameraDevice.close()
    }

    private fun getOutputFilePath(): String {
        // Define the output file path here
        // For example:
        // return Environment.getExternalStorageDirectory().toString() + "/myvideo.mp4"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dumbbell)
    } */