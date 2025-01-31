package com.app.animemovies.data.repository

import com.app.animemovies.data.model.AnimeDetailsResponseDto
import com.app.animemovies.data.model.TopAnimeResponseDto
import com.app.animemovies.network.ApiService
import javax.inject.Inject

class MyRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getMovies(): TopAnimeResponseDto.MockResponse {
        return apiService.getMovies()
    }

    suspend fun getMovieDetails(movieId: Int): AnimeDetailsResponseDto.MockResponse {
//        var resp = apiService.getMovieDetails(movieId)
        return apiService.getMovieDetails(movieId)
    }
}