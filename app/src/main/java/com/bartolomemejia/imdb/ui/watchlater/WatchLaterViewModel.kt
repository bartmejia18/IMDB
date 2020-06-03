package com.bartolomemejia.imdb.ui.watchlater

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bartolomemejia.imdb.data.MovieDao
import com.bartolomemejia.imdb.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WatchLaterViewModel @Inject constructor(val movieDao: MovieDao) : ViewModel() {
    val watchLaterMovieList = MutableLiveData<List<Movie>>()


    init {
        CoroutineScope(Dispatchers.IO).launch {
            val favorites = movieDao.getWatchLaterList()
            if (favorites.isNotEmpty()) {
                watchLaterMovieList.postValue(favorites)
            }
        }
    }
}
