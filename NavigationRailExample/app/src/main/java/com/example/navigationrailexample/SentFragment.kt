package com.example.navigationrailexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navigationrailexample.databinding.FragmentSentBinding

class SentFragment : Fragment() {

    lateinit var sentBinding: FragmentSentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sentBinding = FragmentSentBinding.inflate(layoutInflater, container, false)
        return sentBinding.root
    }
}