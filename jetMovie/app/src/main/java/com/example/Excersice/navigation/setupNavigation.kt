package com.example.Excersice.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.Excersice.HomeScreen
import com.example.Excersice.detailScreen


@Composable
fun setupNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = movieScreen.homeScreen.name
    ) {
        composable(movieScreen.homeScreen.name)
        {
                HomeScreen(navController = navController)
        }
        composable(movieScreen.detailsScreen.name+"/{movie}",
            arguments = listOf(
                navArgument(
                    name="movie"
                ){

                    type= NavType.StringType

                }
            )
        ){
            detailScreen(navController = navController
                ,it.arguments?.getString("movie"))
        }
    }
}