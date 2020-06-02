package com.bartolomemejia.imdb.utils

const val API_KEY_STRING = "c5d79ab0921985107d4743c1f7fca36a"
const val BASE_URL = "https://api.themoviedb.org/"
const val IMAGES_URL = "https://image.tmdb.org/t/p/w500"

enum class ParamsEmun(val string: String) {
    KEY("api_key"),
    YEAR("primary_release_year"),
    PAGE("page"),
    QUERY("query")
}