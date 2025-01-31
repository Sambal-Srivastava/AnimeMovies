package com.app.animemovies.network

import com.app.animemovies.data.model.AnimeDetailsResponseDto
import com.app.animemovies.data.model.TopAnimeResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("top/anime")
    suspend fun getMovies(): TopAnimeResponseDto.MockResponse

    @GET("anime/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: Int): AnimeDetailsResponseDto.MockResponse
}