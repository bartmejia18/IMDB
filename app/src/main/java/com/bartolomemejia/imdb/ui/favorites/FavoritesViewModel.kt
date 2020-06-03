package com.bartolomemejia.imdb.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bartolomemejia.imdb.data.MovieDao
import com.bartolomemejia.imdb.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(val movieDao: MovieDao) : ViewModel() {
    val favoriteList = MutableLiveData<List<Movie>>()
    val selectedMovie = MutableLiveData<Movie>()

    fun loadListFavorites() {
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = movieDao.getFavorites()
            if (favorites.isNotEmpty()) {
                favoriteList.postValue(favorites)
            }
        }
    }
}
