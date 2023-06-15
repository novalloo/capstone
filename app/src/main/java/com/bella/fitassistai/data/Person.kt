package com.bella.fitassistai.data

import android.graphics.RectF

data class Person(
    var id: Int = -1,
    val keyPoints: List<KeyPoint>,
    val boundingBox: RectF? = null,
    val score: Float
)
