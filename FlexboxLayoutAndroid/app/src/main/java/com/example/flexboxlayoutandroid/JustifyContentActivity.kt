package com.example.flexboxlayoutandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.JustifyContent

class JustifyContentActivity : AppCompatActivity() {
    lateinit var btnFlexStart: AppCompatButton
    lateinit var btnFlexEnd: AppCompatButton
    lateinit var btnCenter: AppCompatButton

    lateinit var btnSpaceBetween: AppCompatButton
    lateinit var btnSpaceAround: AppCompatButton
    lateinit var btnSpaceEvenly: AppCompatButton
    lateinit var flexboxLayout: FlexboxLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_justify_content)

        btnFlexStart = findViewById(R.id.actionFlexStart)
        btnFlexEnd = findViewById(R.id.actionFlexEnd)
        btnCenter = findViewById(R.id.actionCenter)

        btnSpaceBetween = findViewById(R.id.actionSpaceBetween)
        btnSpaceAround = findViewById(R.id.actionSpaceAround)
        btnSpaceEvenly = findViewById(R.id.actionSpaceEvenly)
        flexboxLayout = findViewById(R.id.flexLayout)
        btnFlexStart.isSelected = true


        btnFlexStart.setOnClickListener {
            resetActions()
            btnFlexStart.isSelected = true
            flexboxLayout.justifyContent = JustifyContent.FLEX_START
        }
        btnFlexEnd.setOnClickListener {
            resetActions()
            btnFlexEnd.isSelected = true
            flexboxLayout.justifyContent = JustifyContent.FLEX_END
        }

        btnCenter.setOnClickListener {
            resetActions()
            btnCenter.isSelected = true
            flexboxLayout.justifyContent = JustifyContent.CENTER
        }

        btnSpaceAround.setOnClickListener {
            resetActions()
            btnSpaceAround.isSelected = true
            flexboxLayout.justifyContent = JustifyContent.SPACE_AROUND
        }

        btnSpaceBetween.setOnClickListener {
            resetActions()
            btnSpaceBetween.isSelected = true
            flexboxLayout.justifyContent = JustifyContent.SPACE_BETWEEN
        }
        btnSpaceEvenly.setOnClickListener {
            resetActions()
            btnSpaceEvenly.isSelected = true
            flexboxLayout.justifyContent = JustifyContent.SPACE_EVENLY
        }

    }

    private fun resetActions() {
        btnFlexStart.isSelected = false
        btnFlexEnd.isSelected = false
        btnCenter.isSelected = false

        btnSpaceAround.isSelected = false
        btnSpaceBetween.isSelected = false
        btnSpaceEvenly.isSelected = false
    }
}