package com.velmurugan.stepperwithindicatorusingviewpagerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.velmurugan.stepperwithindicatorusingviewpagerandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.stepper.addOnStepClickListener { position ->
            binding.viewPager.currentItem = position
        }

        binding.viewPager.setAllowedSwipeDirection(SwipeDirection.ALL)
        binding.stepper.setLabels(listOf<String>("Step 1", "Step 2", "Step 3", "Step 4").toTypedArray())
        binding.stepper.showLabels(true)
        binding.viewPager.adapter = PagerAdapter(supportFragmentManager)
        binding.stepper.setViewPager(binding.viewPager)
    }



}