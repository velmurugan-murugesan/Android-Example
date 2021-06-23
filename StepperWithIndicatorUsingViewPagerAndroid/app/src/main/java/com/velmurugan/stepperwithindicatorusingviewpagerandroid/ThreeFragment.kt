package com.velmurugan.stepperwithindicatorusingviewpagerandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.velmurugan.stepperwithindicatorusingviewpagerandroid.databinding.FragmentOneBinding
import com.velmurugan.stepperwithindicatorusingviewpagerandroid.databinding.FragmentThreeBinding

class ThreeFragment : Fragment() {

    lateinit var binding: FragmentThreeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentThreeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}