package com.example.jetpackcomposenavigationexample.ui.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.jetpackcomposenavigationexample.R
import com.example.jetpackcomposenavigationexample.base.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "app icon")
        Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.titleMedium)
    }

    LaunchedEffect(true) {
        delay(2000)
        navHostController.navigate(Screens.home) {
            popUpTo(Screens.splash) {
                inclusive = true
            }
        }
    }

}

