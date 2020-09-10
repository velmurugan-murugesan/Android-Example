package com.example.progressindicatorandroid

import android.app.ActionBar
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.progressindicator.ProgressIndicator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var value = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Create the progress indicator
        val progressIndicator = ProgressIndicator(
            this, null, 0,
            R.style.Widget_MaterialComponents_ProgressIndicator_Linear_Determinate)
        // add progress indicator into layout
        parentLayout.addView(progressIndicator, LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT))
        // set the progress
        progressIndicator.progress = 40
        // display the progress dialog
        progressIndicator.show()
        /*val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                //Call your function here
                value += 1
                progressLinearDeterminate.progress = value
                if(value != 100) {
                    handler.postDelayed(this, 200)//1 sec delay
                }
            }
        }, 0)*/
    }
}