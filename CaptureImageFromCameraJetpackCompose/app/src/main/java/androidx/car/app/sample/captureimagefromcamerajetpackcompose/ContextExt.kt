package androidx.car.app.sample.captureimagefromcamerajetpackcompose

import android.content.Context
import android.net.Uri
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    // Save a file: path for use with ACTION_VIEW intents
    //mCurrentPhotoPath = image.absolutePath
    return image
}


fun Context.getFile(uri: Uri) : File {
    val path = uri.path?.replace("/my_images","")

    return File(externalCacheDir,path)
}