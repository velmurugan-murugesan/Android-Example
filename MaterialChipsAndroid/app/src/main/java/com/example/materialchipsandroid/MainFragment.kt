package com.example.materialchipsandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_entry_chip.setOnClickListener {
            replaceFragment(EntryChipsFragment.newInstance())
        }

        btn_filter_chip.setOnClickListener {
            replaceFragment(FilterChipFragment())
        }

        btn_choice_chip.setOnClickListener {
            replaceFragment(ChoiceChipFragment())
        }

        btn_action_chip.setOnClickListener {
            replaceFragment(ActionChipFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        fragmentManager?.beginTransaction()?.replace(R.id.frame_container, fragment)?.addToBackStack(null)?.commit()
    }

}