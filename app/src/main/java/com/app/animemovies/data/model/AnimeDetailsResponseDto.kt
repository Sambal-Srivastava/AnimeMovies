package com.app.animemovies.data.model

import com.google.gson.annotations.SerializedName

object AnimeDetailsResponseDto {

    data class MockResponse(
        @SerializedName("data") var data: Data? = null
    )

    data class Data(
        @SerializedName("title_english") var title: String? = null,
        @SerializedName("episodes") var episodes: Int? = 0,
        @SerializedName("rating") var rating: String? = null,
        @SerializedName("images") var images: Images? = null,
        @SerializedName("synopsis") var synopsis: String? = null,
        @SerializedName("trailer") var trailer: Trailer? = null,
        @SerializedName("mal_id") var mal_Id: Int? = 0
    )

    data class Jpg(
        @SerializedName("image_url") var imageUrl: String? = null
    )

    data class Trailer(
        @SerializedName("youtube_id") var youtubeId: String? = null,
        @SerializedName("images") var images: Images? = null
    )

    data class Images(
        @SerializedName("jpg") var jpg: Jpg? = null,
        @SerializedName("large_image_url") var largeImageUrl: String? = null
    )
}
