package com.example.jetweather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetweather.components.Screens.mainScreen
import com.example.jetweather.components.Screens.weatherSplashScreen

/*
Navigation Setup
 */
@Composable
fun weatherNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = weatherScreen.splashScreen.name
    )

    {
        /*------------------------------------------------
        Splash Screen
         */
    composable(weatherScreen.splashScreen.name){
        weatherSplashScreen(navController=navController)
    }

        /*----------------------------------------------------------------
        Main Screen
         */
        composable(weatherScreen.mainScreen.name){
            mainScreen(navController=navController)
        }

    }
}
