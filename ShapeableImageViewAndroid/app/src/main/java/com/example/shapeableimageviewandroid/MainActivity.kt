package com.example.shapeableimageviewandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.shape.CornerFamily
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val radius = resources.getDimension(R.dimen.corner_radius)
        val shapeAppearanceModel = shapeableImageView.shapeAppearanceModel.toBuilder()
            .setTopRightCorner(CornerFamily.ROUNDED,radius)
            .setBottomLeftCorner(CornerFamily.ROUNDED, radius)
            .setTopLeftCorner(CornerFamily.ROUNDED, radius)
            .setBottomRightCorner(CornerFamily.ROUNDED,radius)
            .build()
        shapeableImageView.shapeAppearanceModel = shapeAppearanceModel
    }
}