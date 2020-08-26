package com.example.bottomsheetandroid

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_persistent_bottomsheet.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnModalBottomsheet.setOnClickListener {
            MyBottomSheetDialogFragment().apply {
                show(supportFragmentManager, tag)
            }
        }

        val standardBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        standardBottomSheetBehavior.addBottomSheetCallback(object : BottomSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }
        })

        btnPersistentBottomsheet.setOnClickListener {
            standardBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
}