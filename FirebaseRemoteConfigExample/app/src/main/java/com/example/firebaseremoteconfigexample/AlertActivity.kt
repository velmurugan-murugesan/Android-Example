package com.example.firebaseremoteconfigexample

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class AlertActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val c = Calendar.getInstance()
        val time = "${c[Calendar.HOUR]}:${c[Calendar.MINUTE]}"
        c.set(Calendar.HOUR, 22)
        c.set(Calendar.MINUTE, 49)
        val alertTime = "${c[Calendar.HOUR]}:${c[Calendar.MINUTE]}"
        val dd = time.compareTo(alertTime)

        if (dd >= 0) {
            val isPostponded = true
            if(isPostponded) {
                val currentDateTime = 1587929461000
                val cal = Calendar.getInstance()
                cal.add(Calendar.DAY_OF_MONTH, -5) //add a day
                val millis = cal.timeInMillis
                if(millis >= currentDateTime) {
                    showUpdateAlert()
                }
            } else {
                showUpdateAlert()
            }


        } else {

        }



    }

    private fun showUpdateAlert() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Your Title")
        alertDialogBuilder
            .setMessage("Click yes to exit!")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                dialog.dismiss()
                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                } catch (anfe: ActivityNotFoundException) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                }
            }
            .setNegativeButton("Postponed") { dialog, id ->
                dialog.dismiss()
            }
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}