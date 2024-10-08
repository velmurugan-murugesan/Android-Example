package com.teama.pdfviewerusingcoilinandroid

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.teama.pdfviewerusingcoilinandroid.ui.theme.PdfViewerUsingCoilInAndroidTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PdfViewerUsingCoilInAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PdfViewerFromUrl(
                        modifier = Modifier.padding(innerPadding),
                        pdfUrl = "https://file-examples.com/storage/fea570b16e6703ef79e65b4/2017/10/file-sample_150kB.pdf"
                    )
                }
            }
        }
    }
}

@Composable
fun PdfViewerFromUrl(pdfUrl: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var pdfFile by remember { mutableStateOf<File?>(null) }
    var pageCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(pdfUrl) {
        pdfFile = downloadPdf(context, pdfUrl)
    }

    pdfFile?.let { file ->
        val fileDescriptor =
            remember { ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY) }
        val pdfRenderer = remember { PdfRenderer(fileDescriptor) }
        pageCount = pdfRenderer.pageCount

        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(pageCount) { pageIndex ->
                val bitmap = remember { Bitmap.createBitmap(1000, 1200, Bitmap.Config.ARGB_8888) }
                pdfRenderer.openPage(pageIndex).use { page ->
                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                }
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Page ${pageIndex + 1} of PDF",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                pdfRenderer.close()
                fileDescriptor?.close()
                file.delete()
            }
        }
    }
}


suspend fun downloadPdf(context: Context, url: String): File? {
    return withContext(Dispatchers.IO) {
        try {
            val pdfFile = File(context.cacheDir, "temp_pdf_file.pdf")
            val urlConnection = URL(url).openConnection() as HttpURLConnection
            urlConnection.inputStream.use { input ->
                pdfFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
            pdfFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}