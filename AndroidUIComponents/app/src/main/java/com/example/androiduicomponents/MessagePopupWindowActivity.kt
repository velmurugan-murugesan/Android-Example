package com.example.androiduicomponents

import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MessagePopupWindowActivity : AppCompatActivity() {

    private var popupWindow: PopupWindow? = null
    private val popupWindowView: View by lazy {
        layoutInflater.inflate(R.layout.layout_message_popup_window, null)
    }
    private val autoHideHandler: Handler by lazy {
        Handler(Looper.myLooper()!!)
    }

    private val timeDelay = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_popup_window)

        findViewById<Button>(R.id.btnShowErrorPopupWindow).setOnClickListener {
            showPopupMessage("Hello world", MessageType.ERROR, true)
        }

        findViewById<Button>(R.id.btnShowSuccessPopupWindow).setOnClickListener {
            showPopupMessage("Hello world", MessageType.SUCCESS)
        }

        findViewById<Button>(R.id.btnShowWarningPopupWindow).setOnClickListener {
            showPopupMessage("Hello world", MessageType.WARNING)
        }

    }


    private fun showPopupMessage(
        message: String,
        messageType: MessageType,
        autoHide: Boolean = false
    ) {

        if (popupWindow == null) {
            popupWindow = PopupWindow(
                popupWindowView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
            )
        }
        setupPopupWindow(popupWindowView, message, messageType)
        popupWindow?.showAtLocation(popupWindowView, Gravity.TOP, 0, 250)
        if (autoHide) {
            autoHideHandler.removeCallbacksAndMessages(null)
            autoHideHandler.postDelayed(
                {
                    popupWindow?.dismiss()
                }, timeDelay.toLong()
            )
        }
    }

    private fun setupPopupWindow(popupWindowView: View, message: String, messageType: MessageType) {
        val backgroundView =
            popupWindowView.findViewById<ConstraintLayout>(R.id.messagePopupWindowLayout)
        val iconView = popupWindowView.findViewById<ImageView>(R.id.imgError)
        val messageView = popupWindowView.findViewById<TextView>(R.id.tvMessage)
        messageView.text = message

        when (messageType) {
            MessageType.ERROR -> {
                backgroundView.setBackgroundColor(ContextCompat.getColor(this, R.color.light_red))
                iconView.background =
                    ContextCompat.getDrawable(this, R.drawable.ic_baseline_error_24)
            }
            MessageType.SUCCESS -> {
                backgroundView.setBackgroundColor(ContextCompat.getColor(this, R.color.light_green))
                iconView.background =
                    ContextCompat.getDrawable(this, R.drawable.ic_baseline_check_circle_24)
            }
            MessageType.WARNING -> {
                backgroundView.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.light_yellow
                    )
                )
                iconView.background =
                    ContextCompat.getDrawable(this, R.drawable.ic_round_warning_24)
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        popupWindow?.let {
            popupWindow = null
        }
    }

    enum class MessageType {
        ERROR, SUCCESS, WARNING
    }
}