package androidx.car.app.sample.customalertdialogjetpackcompose

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.car.app.sample.customalertdialogjetpackcompose.ui.theme.CustomAlertDialogJetpackComposeTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomAlertDialogJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Content()
                    //Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Content() {
    val context = LocalContext.current
    var showCustomDialog by remember {
        mutableStateOf(false)
    }
    var showLoadingDialog by remember {
        mutableStateOf(false)
    }

    var showInputDialog by remember {
        mutableStateOf(false)
    }

    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showCustomDialog = !showCustomDialog }, Modifier.wrapContentSize()) {
            Text(text = "Show Alert Dialog")
        }

        Button(onClick = { showLoadingDialog = !showLoadingDialog }, Modifier.wrapContentSize()) {
            Text(text = "Show Loading Dialog")
        }

        Button(onClick = { showInputDialog = !showInputDialog }, Modifier.wrapContentSize()) {
            Text(text = "Show Input Dialog")
        }
    }

    if (showCustomDialog) {
        CustomAlertDialog({
            showCustomDialog = !showCustomDialog
        }, {
            val activity = (context as? Activity)
            activity?.finish()
        })
    }

    if (showLoadingDialog) {
        LoadingView {
            showLoadingDialog = !showLoadingDialog
        }
    }

    if (showInputDialog) {
        InputDialogView {
            showInputDialog = !showInputDialog
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomAlertDialogJetpackComposeTheme {
        Content()
    }
}

