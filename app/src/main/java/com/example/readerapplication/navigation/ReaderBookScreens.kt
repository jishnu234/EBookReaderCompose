package com.example.readerapplication.navigation

import java.lang.IllegalArgumentException


enum class ReaderBookScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    HomeScreen,
    DetailScreen,
    UpdateScreen,
    SearchScreen,
    ReaderStatsScreen;

    companion object {
        fun fromRoute(route: String?): ReaderBookScreens =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                LoginScreen.name -> LoginScreen
                HomeScreen.name -> HomeScreen
                CreateAccountScreen.name -> CreateAccountScreen
                DetailScreen.name -> DetailScreen
                UpdateScreen.name -> UpdateScreen
                ReaderStatsScreen.name -> ReaderStatsScreen
                SearchScreen.name -> SearchScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route: $route is not recognized")
            }
    }
}