package com.example.navigationrailexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navigationrailexample.databinding.FragmentDraftBinding

class DraftFragment : Fragment() {

    lateinit var draftBinding: FragmentDraftBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        draftBinding = FragmentDraftBinding.inflate(layoutInflater, container, false)
        return draftBinding.root
    }
}