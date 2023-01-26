package com.example.flexboxlayoutandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout

class FlexWrapActivity : AppCompatActivity() {
    lateinit var flexboxLayout: FlexboxLayout
    lateinit var btnNoWrap: AppCompatButton
    lateinit var btnWrap: AppCompatButton
    lateinit var btnWrapReverse: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flex_wrap)

        btnNoWrap = findViewById(R.id.actionNoWrap)
        btnWrap = findViewById(R.id.actionWrap)
        btnWrapReverse = findViewById(R.id.actionWrapReverse)

        flexboxLayout = findViewById(R.id.flexLayout)
        btnNoWrap.isSelected = true

        btnNoWrap.setOnClickListener {
            resetActions()
            btnNoWrap.isSelected = true
            flexboxLayout.flexWrap = FlexWrap.NOWRAP
        }
        btnWrap.setOnClickListener {
            resetActions()
            btnWrap.isSelected = true
            flexboxLayout.flexWrap = FlexWrap.WRAP
        }
        btnWrapReverse.setOnClickListener {
            resetActions()
            btnWrapReverse.isSelected = true
            flexboxLayout.flexWrap = FlexWrap.WRAP_REVERSE
        }


    }

    private fun resetActions() {
        btnNoWrap.isSelected = false
        btnWrap.isSelected = false
        btnWrapReverse.isSelected = false
    }
}