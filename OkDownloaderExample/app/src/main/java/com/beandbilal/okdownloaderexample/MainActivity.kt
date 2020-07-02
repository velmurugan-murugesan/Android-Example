package com.beandbilal.okdownloaderexample

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.liulishuo.okdownload.DownloadTask


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Log.d("Permission ","You have permission");
            val task = DownloadTask.Builder("url",Environment.DIRECTORY_PICTURES.toString(),"fff")
                    .setMinIntervalMillisCallbackProcess(30) // do re-download even if the task has already been completed in the past.
                    .setPassIfAlreadyCompleted(false)
                    .build()

            //task.enqueue(listener)

// cancel

// cancel
            task.cancel()

// execute task synchronized

// execute task synchronized
            task.execute(listener)

        } else {
            ActivityCompat.requestPermissions(this, arrayOf( Manifest.permission.WRITE_EXTERNAL_STORAGE), 1);
        }


        val listener: OkDownloadEnqueueListener = object : OkDownloadEnqueueListener() {
            fun onStart(id: Int) {
                Log.e("OkDownload", "onStart : the download request id = $id")
            }

            fun onProgress(
                progress: Int,
                cacheSize: Long,
                totalSize: Long
            ) {
                Log.e("OkDownload", "$cacheSize/$totalSize")
            }

            fun onRestart() {
                Log.e("OkDownload", "onRestart")
            }

            fun onPause() {
                Log.e("OkDownload", "onPause")
            }

            fun onCancel() {
                Log.e("OkDownload", "onCancel")
            }

            fun onFinish() {
                Log.e("OkDownload", "onFinish")
            }

            fun onError(error: OkDownloadError) {
                Log.e("OkDownload", error.getMessage())
            }
        }

    }
}
