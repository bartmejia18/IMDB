package com.bartolomemejia.imdb.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movies (
    @Json(name = "total_pages") val totalPages: Int,
    val results: List<Movie>
)
