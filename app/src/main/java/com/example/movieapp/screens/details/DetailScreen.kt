package com.example.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.MovieRow

@Composable
fun DetailScreen(navController: NavController, movieData: String?) {

    // Filter all movies to retrieve only the one with our id value
    val movieList = getMovies().filter { movie ->
        movie.id == movieData
    }
    
    Scaffold(topBar = { TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
        
        Row(horizontalArrangement = Arrangement.Start) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Button",
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable { navController.popBackStack() })
            
            Spacer(modifier = Modifier.width(120.dp))
            
            Text(text = "Movie Details")
        }
        
    }}) {

        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top) {

                MovieRow(movie = movieList.first())

                Spacer(modifier = Modifier.height(8.dp))

                Divider()

                Text(text = "Movie Images")

                HorizontalScrollableImageView(movieList)

            }

        }
        
    }
    
}

@Preview
@Composable
private fun HorizontalScrollableImageView(movieList: List<Movie> = getMovies()) {
    LazyRow {
        items(movieList.first().images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp)) {

                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "Movie Images"
                )

            }
        }
    }
}