package com.bartolomemejia.imdb.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bartolomemejia.imdb.model.Movie
import com.bartolomemejia.imdb.repositories.MoviesRepository
import javax.inject.Inject

class MoviesViewModel @Inject constructor(val moviesRepository: MoviesRepository) : ViewModel() {
    val movieList = moviesRepository.listOfMovies
    var page = MutableLiveData(1)
    val selectedMovie = MutableLiveData<Movie>()

    fun updateMovieList(){
        page.value = 1
        moviesRepository.fetchMovieListData(page.value!!)
    }
}
