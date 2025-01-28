package com.app.animemovies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.animemovies.data.model.MockResponseDto
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

    private val _movies = MutableStateFlow<MockResponseDto.MockResponse?>(null)
    val movies: StateFlow<MockResponseDto.MockResponse?> = _movies

    /*private val _movieDetails = MutableStateFlow<MockResponseDto.MockResponse?>(null)
    val movieDetails: StateFlow<MockResponseDto.MovieDetailsResponse?> = _movieDetails.asStateFlow()
*/
    private val _isLoading = MutableLiveData<Boolean>()  // Loading state
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

    fun fetchMovieDetails(movieId: String, apiKey:String) {
        viewModelScope.launch {
            _isLoading.value = true  // Show progress bar
            try {
//                _movieDetails.value = myRepository.getMovieDetails(movieId, apiKey)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false  // Hide progress bar
            }
        }
    }
}