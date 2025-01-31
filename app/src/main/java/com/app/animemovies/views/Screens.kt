package com.app.animemovies.views

sealed class Screens {
    @kotlinx.serialization.Serializable
    data object MovieListing: Screens()

    @kotlinx.serialization.Serializable
    data class MovieDetails(val id: Int): Screens()
}