package com.app.animemovies.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.app.animemovies.ui.theme.AnimeMoviesTheme
import com.app.animemovies.viewmodels.MoviesViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun MovieDetails(moviesId: Int, modifier: Modifier = Modifier) {
    val viewModel: MoviesViewModel = hiltViewModel()

    // Trigger API call when moviesId changes
    LaunchedEffect(key1 = moviesId) {
        viewModel.fetchMovieDetails(movieId = moviesId)
    }

    // Observe movie details and loading state
    val movieDetails by viewModel.movieDetails.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 10.dp)
            .verticalScroll(scrollState)
    ) {
        if (isLoading) {
            // Show a loading indicator while the API call is in progress
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (movieDetails != null) {
            if (movieDetails?.data?.trailer?.youtubeId.isNullOrBlank()) {
                AsyncImage(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally),
                    model = movieDetails?.data?.trailer?.images?.largeImageUrl,
                    contentDescription = null,
                )
            } else {
                LiveTvScreen(videoId = movieDetails?.data?.trailer?.youtubeId.toString())
            }
            MultiStyleText(
                stylingString = "Title:",
                appendingString = movieDetails?.data?.title.toString()
            )

            MultiStyleText(
                stylingString = "Synopsis:",
                appendingString = movieDetails?.data?.synopsis.toString()
            )

            MultiStyleText(
                stylingString = "Episodes:",
                appendingString = movieDetails?.data?.episodes.toString()
            )

            MultiStyleText(
                stylingString = "Rating:",
                appendingString = movieDetails?.data?.rating.toString()
            )
        } else {
            // Show an error message or placeholder if no data is available
            Text("No data available")
        }
    }
}

@Composable
fun MultiStyleText(stylingString: String, appendingString: String) {
    Text(
        modifier = Modifier.padding(10.dp),
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("$stylingString \n")
            }
            append(appendingString)
        }
    )
}

@Composable
fun LiveTvScreen(
    videoId: String
) {
    val ctx = LocalContext.current
    AndroidView(factory = {
        var view = YouTubePlayerView(it)
        val fragment = view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(
                    youTubePlayer:
                    YouTubePlayer
                ) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            }
        )
        view
    })
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
    AnimeMoviesTheme {
        MovieDetails(0)
    }
}
