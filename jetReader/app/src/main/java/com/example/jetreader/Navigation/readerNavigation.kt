package com.example.jetreader.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetreader.Screens.details.readerBookDetails
import com.example.jetreader.Screens.home.readerBookHome
import com.example.jetreader.Screens.login.readerBookLogin
import com.example.jetreader.Screens.login.viewModel.viewModelLogin
import com.example.jetreader.Screens.splash.readerBookSplash
import com.example.jetreader.Screens.stats.readerBookStats


@Composable
fun readerNavigation(

    navConroller : NavHostController
) {

    NavHost(
        navController = navConroller,
        startDestination = readerScreens.splashScreen.name
    ) {

        composable(readerScreens.splashScreen.name) {
            readerBookSplash(navConroller)
        }

        composable(readerScreens.homeScreen.name) {
            readerBookHome(navConroller)
        }

        composable(readerScreens.detailScreen.name) {
            readerBookDetails(navConroller)
        }

        composable(readerScreens.loginScreen.name) {
            readerBookLogin(navConroller)
        }

        composable(
            readerScreens.statsScreen.name
        ) {
            readerBookStats(navController = navConroller)
        }

        composable(readerScreens.createAccountScreen.name){


        }
    }
}