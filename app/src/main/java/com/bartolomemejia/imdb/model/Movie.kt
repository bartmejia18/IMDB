package com.bartolomemejia.imdb.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name = "id") val movieId: Int,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "vote_average") val voteAverage: Double,
    val title: String,
    val overview: String,
    var isFavorite: Boolean = false,
    var watchLater: Boolean = false
)
