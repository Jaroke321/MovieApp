package com.example.movieapp.navigation

import java.lang.IllegalArgumentException

enum class MovieScreens {
    HomeScreen,
    DetailScreen;

    companion object {

        fun fromRoute(route: String?): MovieScreens
         = when (route?.substringBefore(delimiter = "/")) {
             HomeScreen.name -> HomeScreen
            DetailScreen.name -> DetailScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
         }

    }
}