package com.app.animemovies.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.app.animemovies.data.model.TopAnimeResponseDto
import com.app.animemovies.ui.theme.AnimeMoviesTheme
import com.app.animemovies.viewmodels.MoviesViewModel

@Composable
fun MovieListing(
    onItemClicked: (movieId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var viewModel: MoviesViewModel = hiltViewModel()
    lateinit var moviesList: List<TopAnimeResponseDto.MockResponse>
    LaunchedEffect(key1 = null) {
        viewModel.fetchMovies()
    }
    val movies by viewModel.movies.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            val movieData = movies?.data ?: emptyList()
            items(movieData.size) { index ->
                val movie = movieData[index]
                Card(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .clickable {
                                onItemClicked(movie.malId!!)
                            }
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            model = movie.images?.jpg?.largeImageUrl,
                            contentDescription = null,
                        )

                        MultiStyleText(
                            stylingString = "Title:",
                            appendingString = movie.title.toString()
                        )

                        MultiStyleText(
                            stylingString = "Episodes:",
                            appendingString = movie.episodes.toString()
                        )

                        MultiStyleText(
                            stylingString = "Rating:",
                            appendingString = movie.rating.toString()
                        )
                    }
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
