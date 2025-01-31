package com.app.animemovies.data.model

import com.google.gson.annotations.SerializedName

object TopAnimeResponseDto {

    data class MockResponse(
        @SerializedName("data") var data: List<Data>? = null
    )

    data class Data(
        @SerializedName("title_english") var title: String? = null,
        @SerializedName("episodes") var episodes: Int? = 0,
        @SerializedName("rating") var rating: String? = null,
        @SerializedName("images") var images: Images? = null,
        @SerializedName("mal_id") var malId: Int? = 0
    )

    data class Images(
        @SerializedName("jpg") var jpg: Jpg? = null
    )

    data class Jpg(
        @SerializedName("image_url") var imageUrl: String? = null,
        @SerializedName("large_image_url") var largeImageUrl: String? = null
    )
}
