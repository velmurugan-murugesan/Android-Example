package com.velmurugan.workmanagerandroid

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*

class MainActivity : AppCompatActivity() {

    private var downloadedImageUri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewNormal = findViewById<ImageView>(R.id.imageViewNormal)
        val imageViewBlur = findViewById<ImageView>(R.id.imageViewBlur)
        val blurImage = findViewById<Button>(R.id.blurImage)
        val downloadImage = findViewById<Button>(R.id.downloadImage)

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java).build()
        val blurRequest = OneTimeWorkRequest.Builder(BlurWorker::class.java)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.id).observe(this, { workInfo ->
            val imageUri = workInfo?.outputData?.getString(KEY_IMAGE_URI)
           imageUri?.let {
               imageViewNormal.setImageURI(Uri.parse(imageUri))
               downloadedImageUri = it
           }
        })

        downloadImage.setOnClickListener {
            WorkManager.getInstance(this).enqueue(oneTimeWorkRequest)

            WorkManager.getInstance(this).
            beginWith(oneTimeWorkRequest)
                    .then(blurRequest.build())
                    .enqueue()
        }

        blurImage.setOnClickListener {
            val builder = Data.Builder()
            builder.putString(KEY_IMAGE_URI, downloadedImageUri)
            blurRequest.setInputData(builder.build())
            val blurBuilder = blurRequest.build()
            WorkManager.getInstance(this).getWorkInfoByIdLiveData(blurBuilder.id).observe(this@MainActivity, { workInfo2 ->
                val imageUri2 = workInfo2?.outputData?.getString(KEY_IMAGE_URI)
                imageUri2?.let {
                    imageViewBlur.setImageURI(Uri.parse(imageUri2))
                }
            })
            WorkManager.getInstance(this@MainActivity).enqueue(blurBuilder)
        }

    }
}