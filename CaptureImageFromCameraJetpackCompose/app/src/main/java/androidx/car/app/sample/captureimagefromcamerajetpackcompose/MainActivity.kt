package androidx.car.app.sample.captureimagefromcamerajetpackcompose

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.car.app.sample.captureimagefromcamerajetpackcompose.ui.theme.CaptureImageFromCameraJetpackComposeTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptureImageFromCameraJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val context = LocalContext.current

                    AppContent()
                    //check permission
                    val permissionStatus = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    )
                    if(permissionStatus != PackageManager.PERMISSION_GRANTED) {
                        rememberLauncherForActivityResult(
                            ActivityResultContracts.RequestPermission()
                        ) {
                        }.launch(Manifest.permission.CAMERA)
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AppContent() {

    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider", file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
        }

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            launcher.launch(uri)
        }) {
            Text(text = "Capture Image From Camera")
        }
    }

    if (capturedImageUri.path?.isNotEmpty() == true) {
        Image(
            modifier = Modifier
                .padding(16.dp, 8.dp)
                .size(100.dp),
            painter = rememberImagePainter(capturedImageUri),
            contentDescription = null
        )
    }





}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CaptureImageFromCameraJetpackComposeTheme {
        AppContent()
    }
}