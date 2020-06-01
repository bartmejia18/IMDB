package com.bartolomemejia.imdb.ui.movies

import androidx.lifecycle.ViewModel
import com.bartolomemejia.imdb.repositories.MoviesRepository
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val moviesRepository: MoviesRepository) : ViewModel() {
    val movieList = moviesRepository.listOfMovies
    fun updateMovieList() = moviesRepository.fetchPokeListData()
}
