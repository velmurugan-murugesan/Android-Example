package com.velmurugan.stepperwithindicatorusingviewpagerandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.velmurugan.stepperwithindicatorusingviewpagerandroid.databinding.FragmentOneBinding

class OneFragment : Fragment() {

    lateinit var binding: FragmentOneBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentOneBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}