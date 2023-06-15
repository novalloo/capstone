package com.bella.fitassistai.movenet

import android.graphics.Bitmap
import com.bella.fitassistai.data.Person

interface PoseDetector : AutoCloseable {

    fun estimatePoses(bitmap: Bitmap): List<Person>

    fun lastInferenceTimeNanos(): Long
}
