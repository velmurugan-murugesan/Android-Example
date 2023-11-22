package com.example.jetpackcomposenavigationexample.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcomposenavigationexample.base.Screens.details
import com.example.jetpackcomposenavigationexample.ui.features.details.DetailsScreen
import com.example.jetpackcomposenavigationexample.ui.features.home.HomeScreen
import com.example.jetpackcomposenavigationexample.ui.features.splash.SplashScreen

@Composable
fun AppNavState(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.splash
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {


        composable(Screens.splash) {
            SplashScreen(navController)
        }
        composable(Screens.home) {
            HomeScreen(navController)
        }

        composable(
            details,
            arguments = listOf(navArgument("title") { type = NavType.StringType } )
        ) {
            val title = it.arguments?.getString("title")
            DetailsScreen(navController, title)
        }

       /* composable(Screens.step1BasicDetails) {
            Step1BasicDetailsScreen(navController)
        }

        composable(
            step2CreateNewMeasurement,
            arguments = listOf(navArgument("isEdit") { type = NavType.BoolType } )
        ) {
            val isEdit = it.arguments?.getBoolean("isEdit") ?: false
            Step2CreateNewMeasurementScreen(navController, isEdit, viewModel)
        }

        composable(Screens.step3MeasurementList) {
            Step3MeasurementListScreen(navController, viewModel)
        }

        composable(Screens.step4ReviewMeasurement) {
            Step4ReviewMeasurementScreen(navController)
        }*/

        /*
        composable(Screens.step3ReviewMeasurement) {
            Step3ReviewMeasurementScreen(navController)
        }

        composable(Screens.step4AdditionalDetails) {
            Step4AdditionalDetailsScreen(navController)
        }*/
    }
}

object Screens {
    const val splash = "Splash"
    const val home = "Home"
    const val details = "Details/{title}"


    fun getStep2Screen(title: String): String {
        return "Details/$title"
    }

}