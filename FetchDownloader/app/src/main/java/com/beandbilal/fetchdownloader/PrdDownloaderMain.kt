package com.beandbilal.fetchdownloader

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.downloader.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.Error

class PrdDownloaderMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start_download.setOnClickListener {

            val downloadId: Int = PRDownloader.download("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", getSaveDir(), "${System.currentTimeMillis()}.mp4")
                .build()
                .setOnStartOrResumeListener(object : OnStartOrResumeListener {
                    override fun onStartOrResume() {}
                })
                .setOnPauseListener(object : OnPauseListener {
                    override fun onPause() {}
                })
                .setOnCancelListener(object : OnCancelListener {
                    override fun onCancel() {}
                })
                .setOnProgressListener(object : OnProgressListener {
                    override fun onProgress(progress: Progress?) {

                        Log.d("progress", progress!!.totalBytes.toString())

                    }
                })
                .start(object : OnDownloadListener {
                    override fun onDownloadComplete() {}
                    override fun onError(error: com.downloader.Error?) {

                    }

                })
        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            Toast.makeText(
                this,
                "Write External Storage permission allows us to save files. Please allow this permission in App Settings.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                100
            )
        }
    }
    @NonNull
    fun getSaveDir(): String? {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .toString()
    }



}