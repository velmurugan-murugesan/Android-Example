package com.example.jetpacksecuritylibary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.*

class MainActivity : AppCompatActivity() {

    private val file by lazy { File(filesDir, ENCRYPTED_FILE_NAME) }
    private val file2 by lazy { File(filesDir, "1$ENCRYPTED_FILE_NAME") }

    private val encryptedFile by lazy {
        EncryptedFile.Builder(
            file,
            applicationContext,
            masterKeyAlias,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListeners()
    }

    private fun setupListeners() {

        fileDownload.setOnClickListener { downloadAndEncryptFile() }
        fileDecrypt.setOnClickListener {
            readFile { encryptedFile.openFileInput() }
        }
    }

    private fun downloadAndEncryptFile() {
        if (file.exists()) {
            Log.i("TAG", "Encrypted file already exists!")
            result.text = getString(R.string.file_exists)
        } else {
            Log.e("TAG", "Encrypted file does not exist exists! Downloading...")
            val request = Request.Builder().url(FILE_URL).build()
            okHttpClient.newCall(request).enqueue(
                object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        result.text = e.message
                        Log.e("TAG", "Error occurred!", e)
                    }

                    override fun onResponse(call: Call, response: Response) {
                        onFileDownloaded(response.body!!.bytes())
                    }
                }
            )
        }
    }

    private fun onFileDownloaded(bytes: ByteArray) {
        var encryptedOutputStream: FileOutputStream? = null
        try {
            encryptedOutputStream = encryptedFile.openFileOutput().apply {
                write(bytes)
            }
            result.text = getString(R.string.file_downloaded)
        } catch (e: Exception) {
            Log.e("TAG", "Could not open encrypted file", e)
            result.text = e.message
        } finally {
            encryptedOutputStream?.close()
        }
    }

    private fun readFile(fileInput: () -> FileInputStream) {
        Log.i("TAG", "Loading file...")

        var fileInputStream: FileInputStream? = null


        try {
            fileInputStream = fileInput()
            val fos = FileOutputStream(file2)
            val buffer = ByteArray(1024)
            var len1 = 0
            while (fileInputStream.read(buffer).also { len1 = it } > 0) {
                fos.write(buffer, 0, len1)
            }

            fileInputStream.close()
            fos.close()
        } catch (e: FileNotFoundException) {
            System.err.println("FileStreamsTest: $e")
        } catch (e: IOException) {
            System.err.println("FileStreamsTest: $e")
        } finally {
            val uri = FileProvider.getUriForFile(
                this@MainActivity,
                BuildConfig.APPLICATION_ID + ".provider",
                file2
            )

            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(uri, "application/pdf")
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(intent)
        }
    }

    companion object {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
        const val ENCRYPTED_FILE_NAME = "SHORTCUT4.pdf"
        const val FILE_URL =
            "https://faculty.kfupm.edu.sa/ee/ibrahimh/misc/COMPUTER%20SHORTCUTS.pdf"
    }
}
