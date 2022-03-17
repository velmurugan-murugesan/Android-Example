package com.velmurugan.runtimepermissionandroid

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import java.security.Permission

class MainActivity : AppCompatActivity() {

    lateinit var btnCamera: Button
    lateinit var btnLocation: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCamera = findViewById(R.id.btnCamera)
        btnLocation = findViewById(R.id.btnLocation)

        btnCamera.setOnClickListener {

            if (checkPermission(Manifest.permission.CAMERA)) {
                //openCamera()
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    AlertDialog.Builder(this)
                        .setMessage("Need camera permission to capture image. Please provide permission to access your camera.")
                        .setPositiveButton("OK") { dialogInterface, i ->
                            dialogInterface.dismiss()
                            ActivityCompat.requestPermissions(
                                this,
                                arrayOf(Manifest.permission.CAMERA),
                                201
                            )
                        }
                        .setNegativeButton("Cancel") { dialogInterface, i ->
                            dialogInterface.dismiss()
                        }
                        .create()
                        .show();
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE),
                        201
                    )
                }
            }


            /**/

        }

    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 201) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                //openCamera()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

}