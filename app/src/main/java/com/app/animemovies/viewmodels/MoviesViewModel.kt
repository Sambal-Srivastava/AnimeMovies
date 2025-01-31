package com.app.animemovies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.animemovies.data.model.AnimeDetailsResponseDto
import com.app.animemovies.data.model.TopAnimeResponseDto
import com.app.animemovies.data.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val myRepository: MyRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<TopAnimeResponseDto.MockResponse?>(null)
    val movies: StateFlow<TopAnimeResponseDto.MockResponse?> = _movies

    private val _movieDetails = MutableLiveData<AnimeDetailsResponseDto.MockResponse?>()
    val movieDetails: LiveData<AnimeDetailsResponseDto.MockResponse?> = _movieDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchMovies() {
        viewModelScope.launch {
            _isLoading.value = true  // Show progress bar
            try {
                _movies.value = myRepository.getMovies()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false  // Hide progress bar
            }
        }
    }

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _isLoading.value = true  // Show progress bar
            try {
                _movieDetails.value = myRepository.getMovieDetails(movieId)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false  // Hide progress bar
            }
        }
    }
}