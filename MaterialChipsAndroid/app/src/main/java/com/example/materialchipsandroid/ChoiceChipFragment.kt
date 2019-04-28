package com.example.materialchipsandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_choice_chip.*

class ChoiceChipFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_choice_chip, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        chip_group_choice.setOnCheckedChangeListener { group, checkedId ->

            val chip: Chip? = group.findViewById(checkedId)

            chip?.let {chipView ->
                Toast.makeText(context, chip.text, Toast.LENGTH_SHORT).show()
            } ?: kotlin.run {
            }
        }
    }


}
