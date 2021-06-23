package com.velmurugan.stepperwithindicatorusingviewpagerandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.velmurugan.stepperwithindicatorusingviewpagerandroid.databinding.FragmentOneBinding
import com.velmurugan.stepperwithindicatorusingviewpagerandroid.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {

    lateinit var binding: FragmentTwoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTwoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}