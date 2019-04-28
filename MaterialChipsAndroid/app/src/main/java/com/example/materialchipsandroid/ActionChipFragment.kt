package com.example.materialchipsandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.layout_action_chip.*

class ActionChipFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.layout_action_chip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        chip_alarm.setOnClickListener {
            Toast.makeText(context, "Alarm", Toast.LENGTH_SHORT).show()
        }

        chip_music.setOnClickListener {
            Toast.makeText(context, "Music", Toast.LENGTH_SHORT).show()
        }

        chip_call.setOnClickListener {
            Toast.makeText(context, "Call", Toast.LENGTH_SHORT).show()
        }

        chip_message.setOnClickListener {
            Toast.makeText(context, "Message", Toast.LENGTH_SHORT).show()
        }
    }


}
