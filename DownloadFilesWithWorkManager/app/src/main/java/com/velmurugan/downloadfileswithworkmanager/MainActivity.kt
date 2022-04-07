package com.velmurugan.downloadfileswithworkmanager

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.work.*


class MainActivity : AppCompatActivity() {

    lateinit var workManager: WorkManager
    lateinit var btnOpenFile: Button
    lateinit var btnStartDownload: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        workManager = WorkManager.getInstance(this)

        btnOpenFile = findViewById(R.id.btnOpenFile)
        btnStartDownload = findViewById(R.id.btnStartDownload)



        btnStartDownload.setOnClickListener {
            startDownloadingFile()
            btnOpenFile.visibility = View.GONE
        }

        if (!checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE) && !checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                201
            )
        }

    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun startDownloadingFile(
    ) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresStorageNotLow(true)
            .setRequiresBatteryNotLow(true)
            .build()
        val data = Data.Builder()

        data.apply {
            putString(KEY_FILE_NAME, "sample.pdf")
            putString(KEY_FILE_URL, "http://www.africau.edu/images/default/sample.pdf")
            putString(KEY_FILE_TYPE, "PDF")
        }

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(FileDownloadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data.build())
            .build()

        workManager.enqueue(oneTimeWorkRequest)

        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this) { info ->
                info?.let {
                    when (it.state) {
                        WorkInfo.State.SUCCEEDED -> {
                            val uri = it.outputData.getString(KEY_FILE_URI)
                            uri?.let {
                                btnOpenFile.text = "Open File"
                                btnOpenFile.visibility = View.VISIBLE
                                btnOpenFile.setOnClickListener {
                                    val intent = Intent(Intent.ACTION_VIEW)
                                    intent.setDataAndType(uri.toUri(), "application/pdf")
                                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                    try {
                                        startActivity(intent)
                                    } catch (e: ActivityNotFoundException) {
                                        Toast.makeText(
                                            this@MainActivity,
                                            "Can't open Pdf",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                        WorkInfo.State.FAILED -> {

                            btnOpenFile.text = "Download in failed"
                        }
                        WorkInfo.State.RUNNING -> {
                            btnOpenFile.text = "Download in progress.."
                        }
                        else -> {

                        }
                    }
                }
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 201) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                //openCamera()

            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}