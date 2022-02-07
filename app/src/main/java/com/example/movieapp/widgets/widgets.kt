package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {

    // Holds the current state of the card
    var expandedState by remember {
        mutableStateOf(false)
    }

    // Crad holding each movie instance
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            //.height(130.dp)
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        elevation = 6.dp) {

        // Holds image and details
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {

            // Image
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp), shape = RectangleShape) {

                Image(painter = rememberImagePainter(data = movie.images[0], builder = {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                    }),
                    contentDescription = "Movie Poster")

            }

            // All of the details to the right of the image
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title,
                    style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.caption)
                Text(text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.caption)

                // Only display the plot summary if expanded is true
                AnimatedVisibility(visible = expandedState) {
                    // Movie description when expanded
                    Column {
                        // All of the text in the expanded section
                        Text( buildAnnotatedString {

                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp)) {
                                append("Plot: ")
                            }

                            withStyle(style = SpanStyle(color = Color.DarkGray,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light)) {
                                append(movie.plot)
                            }

                        }, modifier = Modifier.padding(6.dp))

                        Divider(modifier = Modifier.padding(3.dp))

                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                        Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.caption)
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.caption)

                    }
                }

                // Arrow for movie details
                Icon(imageVector = if (expandedState) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { // Change the exapnded state if arrow is pressed
                            expandedState = !expandedState
                        },
                    tint = Color.DarkGray)

            } // End Description column

        } // End main row w/ picture and title

    } // End entire card

} // End row