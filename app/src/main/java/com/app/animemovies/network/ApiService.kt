package com.app.animemovies.network

import com.app.animemovies.data.model.MockResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/anime")
    suspend fun getMovies(): MockResponseDto.MockResponse

    /*@GET(".")
    suspend fun getMovieDetails(@Query("i") movieId:String,
                          @Query("apikey") apiKey:String): MockResponseDto.MovieDetailsResponse*/
}