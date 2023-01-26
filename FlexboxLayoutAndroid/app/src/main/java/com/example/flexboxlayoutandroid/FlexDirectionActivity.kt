package com.example.flexboxlayoutandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.FlexboxLayoutManager

class FlexDirectionActivity : AppCompatActivity() {
    lateinit var flexboxLayout: FlexboxLayout
    lateinit var btnRow: AppCompatButton
    lateinit var btnRowReverse: AppCompatButton
    lateinit var btnColumn: AppCompatButton
    lateinit var btnColumnReverse: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flex_direction)

        btnRow = findViewById(R.id.actionRow)
        btnRowReverse = findViewById(R.id.actionRowReverse)
        btnColumn = findViewById(R.id.actionColumn)
        btnColumnReverse = findViewById(R.id.actionColumnReverse)

        flexboxLayout = findViewById(R.id.flexLayout)
        btnRow.isSelected = true

        btnRow.setOnClickListener {
            resetActions()
            btnRow.isSelected = true
            flexboxLayout.flexDirection = FlexDirection.ROW
        }
        btnRowReverse.setOnClickListener {
            resetActions()
            btnRowReverse.isSelected = true
            flexboxLayout.flexDirection = FlexDirection.ROW_REVERSE
        }
        btnColumn.setOnClickListener {
            resetActions()
            btnColumn.isSelected = true
            flexboxLayout.flexDirection = FlexDirection.COLUMN
        }
        btnColumnReverse.setOnClickListener {
            resetActions()
            btnColumnReverse.isSelected = true
            flexboxLayout.flexDirection = FlexDirection.COLUMN_REVERSE
        }

    }

    private fun resetActions() {
        btnRow.isSelected = false
        btnRowReverse.isSelected = false
        btnColumn.isSelected = false
        btnColumnReverse.isSelected = false
    }
}