package com.app.animemovies.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.animemovies.data.model.MockResponseDto
import com.app.animemovies.ui.theme.AnimeMoviesTheme
import com.app.animemovies.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun MovieListing(
    onItemClicked: (movieId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var viewModel: MoviesViewModel = hiltViewModel()
    lateinit var moviesList: List<MockResponseDto.MockResponse>
    viewModel.fetchMovies()
    val movies by viewModel.movies.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            val movieData = movies?.data ?: emptyList()
            items(movieData.size) { index ->
                val movie = movieData[index]
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .clickable {
                            onItemClicked(movie.malId.toString())
                        }
                ) {
                    Text(
                        text = "Title: ${movie.title}"
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MovieListingPreview() {
    AnimeMoviesTheme {
        MovieListing(onItemClicked = {})
    }
}
