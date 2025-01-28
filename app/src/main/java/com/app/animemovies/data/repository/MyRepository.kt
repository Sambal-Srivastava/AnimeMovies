package com.app.animemovies.data.repository

import com.app.animemovies.data.model.MockResponseDto
import com.app.animemovies.network.ApiService
import javax.inject.Inject

class MyRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getMovies(): MockResponseDto.MockResponse {
        return apiService.getMovies()
    }

    /*suspend fun getMovieDetails(movieId: String, apiKey:String): MockResponseDto.MovieDetailsResponse {
        var resp = apiService.getMovieDetails(movieId, apiKey)
        return apiService.getMovieDetails(movieId, apiKey)
    }*/
}