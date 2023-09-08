package com.velmurugan.hiltandroid.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.velmurugan.hiltandroid.R
import com.velmurugan.hiltandroid.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class SplashFragment : Fragment() {

    private val splashViewModel: SplashViewModel by viewModels()
    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        splashViewModel.version.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }

        binding.welcome.setOnClickListener {
           // binding.welcome.text = "Hello 2"
           findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }

        splashViewModel.checkVersion()

    }


    // delay -- time unit: ms

}