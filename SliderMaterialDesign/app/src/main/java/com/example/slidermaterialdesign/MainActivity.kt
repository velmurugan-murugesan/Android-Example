package com.example.slidermaterialdesign

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        continuousSlider.setLabelFormatter { value: Float ->
            return@setLabelFormatter "$${value.roundToInt()}"
        }


        continuousSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being started
                Log.d("onStartTrackingTouch", slider.value.toString())
            }

            override fun onStopTrackingTouch(slider: Slider) {
                // Responds to when slider's touch event is being stopped
                Log.d("onStopTrackingTouch", slider.value.toString())
            }
        })

        continuousSlider.addOnChangeListener(object: Slider.OnChangeListener{
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                Log.d("addOnChangeListener", slider.value.toString())
            }
        })



        rangeSlider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: RangeSlider) {
                val values = rangeSlider.values
                Log.d("onStartTrackingTouch From", values[0].toString())
                Log.d("onStartTrackingTouch T0", values[1].toString())
            }

            override fun onStopTrackingTouch(slider: RangeSlider) {
                val values = rangeSlider.values
                Log.d("onStopTrackingTouch From", values[0].toString())
                Log.d("onStopTrackingTouch T0", values[1].toString())
            }
        })


        rangeSlider.addOnChangeListener(object : RangeSlider.OnChangeListener{

            override fun onValueChange(slider: RangeSlider, value: Float, fromUser: Boolean) {
                val values = rangeSlider.values
                Log.d("From", values[0].toString())
                Log.d("T0", values[1].toString())
            }
        })

        rangeSlider.addOnChangeListener { rangeSlider, value, fromUser ->

            val values = rangeSlider.values

        }
    }
}