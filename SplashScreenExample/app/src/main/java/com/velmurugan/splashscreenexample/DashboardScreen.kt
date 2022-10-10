package com.velmurugan.splashscreenexample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DashBoardScreen(navController: NavController) {

    Column(Modifier.fillMaxSize()) {
        Text(text = Screens.Dashboard,modifier =
        Modifier
            .background(Color.White)
            .padding(8.dp), fontSize = 28.sp)

        Divider(thickness = 2.dp)

        Text(text = "Hello There,",modifier =
        Modifier
            .background(Color.White)
            .padding(8.dp), fontSize = 24.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DashBoardScreen(navController = rememberNavController())
}