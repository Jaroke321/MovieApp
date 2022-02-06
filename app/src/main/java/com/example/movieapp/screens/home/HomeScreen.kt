package com.example.movieapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.MovieRow
import com.example.movieapp.navigation.MovieScreens

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = { TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {

            Text(text = "Movies")

        } // End Top App Bar

    })

    {

        // Inside Scaffold
        MainContent(navController)

    } // End Scaffold
}

@Composable
fun MainContent(navController: NavController, movieList: List<String> = listOf(
    "Avatar",
    "300",
    "Harry Potter",
    "LOTR",
    "Dark Knight",
    "Dune",
    "King Arthur",
    "Malazan Book of the Fallen")) {

    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = movieList) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(route = MovieScreens.DetailScreen.name+"/$movie")
                }
            }
        }
    }
}