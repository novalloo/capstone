package com.bella.fitassistai.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.bella.fitassistai.R

class ButtonNext : AppCompatButton {

    private lateinit var enableBg: Drawable
    private lateinit var disableBg: Drawable
    private var textColor: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = if (isEnabled) enableBg else disableBg

        setTextColor(textColor)
        textSize = 16f
        gravity = Gravity.CENTER
        text = if (isEnabled) "Next" else "Next"
    }

    private fun init() {
        textColor = ContextCompat.getColor(context, R.color.black)
        enableBg = ContextCompat.getDrawable(context, R.drawable.bg_button_enabled) as Drawable
        disableBg = ContextCompat.getDrawable(context, R.drawable.bg_button_disabled) as Drawable
    }
}