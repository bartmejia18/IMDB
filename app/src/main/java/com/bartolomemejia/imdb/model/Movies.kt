package com.bartolomemejia.imdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "cache")
data class Movies (
    @PrimaryKey(autoGenerate = true) val _id: Int?,
    @Json(name = "total_pages") val totalPages: Int,
    val results: List<Movie>
)
