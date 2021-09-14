package com.example.navigationrailexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navigationrailexample.databinding.FragmentTrashBinding

class TrashFragment  : Fragment() {

    lateinit var trashBinding: FragmentTrashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        trashBinding = FragmentTrashBinding.inflate(layoutInflater, container, false)
        return trashBinding.root
    }
}