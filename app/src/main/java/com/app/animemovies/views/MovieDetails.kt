package com.app.animemovies.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.app.animemovies.BuildConfig.BASE_URL
import com.app.animemovies.ui.theme.AnimeMoviesTheme
import com.app.animemovies.viewmodels.MoviesViewModel

@Composable
fun MovieDetails(moviesId: String, modifier: Modifier = Modifier) {
    val viewModel: MoviesViewModel = hiltViewModel()

    LaunchedEffect(key1 = moviesId) {
        moviesId.let{
            viewModel.fetchMovieDetails(movieId = it, apiKey = BASE_URL)
        }
    }

    /*//observe movie details
    val movieDetails by viewModel.movieDetails.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = movieDetails?.poster,
            contentDescription = null,
        )
        Text(
            text = "Hello ${movieDetails?.title}!"
        )
        Text(
            text = "Hello ${movieDetails?.year}!"
        )
    }*/

}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
    AnimeMoviesTheme {
        MovieDetails("")
    }
}
