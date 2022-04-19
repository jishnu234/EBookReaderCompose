package com.example.readerapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.readerapplication.screens.ReaderSplashScreen
import com.example.readerapplication.screens.deatils.ReaderBookDetailScreen
import com.example.readerapplication.screens.home.ReadeHomeScreen
import com.example.readerapplication.screens.login.ReaderLoginScreen
import com.example.readerapplication.screens.search.SearchScreen
import com.example.readerapplication.screens.stats.ReaderStatsScreen


@ExperimentalComposeUiApi
@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination =
    ReaderBookScreens.SplashScreen.name) {

        composable(ReaderBookScreens.SplashScreen.name){
            ReaderSplashScreen(navController = navController)
        }

        composable(ReaderBookScreens.HomeScreen.name) {
            ReadeHomeScreen(navController)
        }

        composable(ReaderBookScreens.LoginScreen.name){
            ReaderLoginScreen(navController)
        }

        composable(ReaderBookScreens.DetailScreen.name){
            ReaderBookDetailScreen(navController)
        }

        composable(ReaderBookScreens.ReaderStatsScreen.name){
            ReaderStatsScreen(navController)
        }

        composable(ReaderBookScreens.SearchScreen.name){
            SearchScreen(navController)
        }

    }
}
