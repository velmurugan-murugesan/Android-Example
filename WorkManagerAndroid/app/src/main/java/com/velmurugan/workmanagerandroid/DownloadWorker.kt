package com.velmurugan.workmanagerandroid

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import okhttp3.OkHttpClient
import okhttp3.Request

class DownloadWorker(val context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        val myImageFileUri: Uri
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("https://i.pinimg.com/originals/49/70/17/497017869c892b73b128ff72f2732035.jpg")
                .build()
        try {
            val response = client.newCall(request).execute()
            val bitmap = BitmapFactory.decodeStream(response.body?.byteStream())
            myImageFileUri = writeBitmapToFile(context,bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure()
        }
        val outputData = workDataOf(KEY_IMAGE_URI to myImageFileUri.toString())

        return Result.success(outputData)
    }
}