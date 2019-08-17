package com.example.myappauth

import android.content.Context
import android.preference.PreferenceManager
import android.content.SharedPreferences
import android.util.Base64
import java.io.*
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


object EncoDecode {

    val PROVIDER = "BC"
    val KEY_SPEC_ALGORITHM = "AES"
    val CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding"
    val SECRET_KEY = "SECRET_KEY"

    val OUTPUT_KEY_LENGTH = 256

    var myPrefs: SharedPreferences? = null

    private// If no key found, Generate a new one //
    val secretKey: SecretKey
        @Throws(NoSuchAlgorithmException::class)
        get() {
            val encodedKey = key
            if (null == encodedKey || encodedKey.isEmpty()) {
                val secureRandom = SecureRandom()
                val keyGenerator = KeyGenerator.getInstance(KEY_SPEC_ALGORITHM)
                keyGenerator.init(OUTPUT_KEY_LENGTH, secureRandom)
                val secretKey = keyGenerator.generateKey()
                saveKey(Base64.encodeToString(secretKey.getEncoded(), Base64.NO_WRAP))
                return secretKey
            }

            val decodedKey = Base64.decode(encodedKey, Base64.NO_WRAP)
            return SecretKeySpec(decodedKey, 0, decodedKey.size, KEY_SPEC_ALGORITHM)
        }

    val key: String?
        get() = myPrefs!!.getString(SECRET_KEY, null)

    fun init(context: Context) {
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Throws(Exception::class)
    fun encode(fileData: ByteArray): ByteArray {
        val data = secretKey.getEncoded()
        val secretKeySpec = SecretKeySpec(data, 0, data.size, KEY_SPEC_ALGORITHM)
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM, PROVIDER)
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, IvParameterSpec(ByteArray(cipher.getBlockSize())))
        return cipher.doFinal(fileData)
    }

    @Throws(Exception::class)
    fun decode(fileData: ByteArray): ByteArray {
        val decrypted: ByteArray
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM, PROVIDER)
        val ivParameterSpec = IvParameterSpec(ByteArray(cipher.getBlockSize()))
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)
        decrypted = cipher.doFinal(fileData)
        return decrypted
    }

    fun saveKey(value: String) {
        myPrefs!!.edit().putString(SECRET_KEY, value).apply()
    }

    fun readFile(filePath: String): ByteArray {
        val contents: ByteArray
        val file = File(filePath)
        val size = file.length() as Int
        contents = ByteArray(size)
        try {
            val buf = BufferedInputStream(
                FileInputStream(file)
            )
            try {
                buf.read(contents)
                buf.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        return contents
    }

    fun saveFile(encodedBytes: ByteArray, path: String) {
        try {
            val file = File(path)
            val bos = BufferedOutputStream(FileOutputStream(file))
            bos.write(encodedBytes)
            bos.flush()
            bos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun shutDown() {
        myPrefs = null
    }
}