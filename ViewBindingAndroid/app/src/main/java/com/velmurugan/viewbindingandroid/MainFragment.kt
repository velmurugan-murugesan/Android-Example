package com.velmurugan.viewbindingandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.velmurugan.viewbindingandroid.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var textView: TextView
    lateinit var button: Button


    lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

}