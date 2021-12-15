package com.example.androiduicomponents

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ButtonStateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_state)


        val btnEnabled = findViewById<Button>(R.id.btnEnabled)
        val btnSelected = findViewById<Button>(R.id.btnSelected)
        val btnDisabled = findViewById<Button>(R.id.btnDisabled)


        btnEnabled.isEnabled = true
        btnSelected.isSelected = true
        btnDisabled.isEnabled = false
    }
}