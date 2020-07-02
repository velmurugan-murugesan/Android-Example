package com.beandbilal.fetchdownloader

import android.Manifest
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.tonyodev.fetch2.*
import com.tonyodev.fetch2.Fetch.Impl.getInstance
import com.tonyodev.fetch2core.DownloadBlock
import com.tonyodev.fetch2core.Func
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.annotations.NotNull


class MainActivity : AppCompatActivity() {

    lateinit var fetch: Fetch
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fetchConfiguration =
            FetchConfiguration.Builder(this)
                .setDownloadConcurrentLimit(3)
                .setNamespace("sdsd")
                .setNotificationManager(object: DefaultFetchNotificationManager(this) {

                    override fun getFetchInstanceForNamespace(namespace: String): Fetch {
                        return fetch;
                    }
                })
                .build()

        fetch = getInstance(fetchConfiguration)
        btn_start_download.setOnClickListener {
            i = i +1
            startDownload(i)
        }


        val fetchListener: FetchListener = object : FetchListener {
            override fun onQueued(
                @NotNull download: Download,
                waitingOnNetwork: Boolean
            ) {
               /* if (request.getId() === download.id) {
                    showDownloadInList(download)
                }*/
            }

            override fun onCompleted(@NotNull download: Download) {
            }
            fun onError(@NotNull download: Download) {
                val error: Error = download.error
            }

            override fun onProgress(
                @NotNull download: Download,
                etaInMilliSeconds: Long,
                downloadedBytesPerSecond: Long
            ) {
               /* if (request.getId() === download.id) {
                    updateDownload(download, etaInMilliSeconds)
                }*/
                val progress = download.progress
                Log.d("Progress ${download.id}", "${progress}")
            }

            override fun onPaused(@NotNull download: Download) {}
            override fun onResumed(@NotNull download: Download) {}
            override fun onStarted(
                download: Download,
                downloadBlocks: List<DownloadBlock>,
                totalBlocks: Int
            ) {
            }

            override fun onWaitingNetwork(download: Download) {
            }

            override fun onAdded(download: Download) {
            }

            override fun onCancelled(@NotNull download: Download) {}
            override fun onRemoved(@NotNull download: Download) {}
            override fun onDeleted(@NotNull download: Download) {}
            override fun onDownloadBlockUpdated(
                download: Download,
                downloadBlock: DownloadBlock,
                totalBlocks: Int
            ) {
            }

            override fun onError(download: Download, error: Error, throwable: Throwable?) {

            }
        }

        fetch.addListener(fetchListener)
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

    private fun startDownload(i: Int) {
        val url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        val file =  getSaveDir() + "/test${i}.mp4"
        val request = Request(url, file)
        request.priority = Priority.HIGH
        request.networkType = NetworkType.ALL
        fetch.enqueue(request, Func<Request>() {
            Log.d("Request", it.url)
        }, Func<Error> {
            Log.d("Error", it.name)
        })
    }

    @NonNull
    fun getSaveDir(): String? {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .toString()
    }
}
