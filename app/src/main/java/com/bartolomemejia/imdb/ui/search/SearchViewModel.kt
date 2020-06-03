package com.bartolomemejia.imdb.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bartolomemejia.imdb.data.MovieDao
import com.bartolomemejia.imdb.model.Movie
import com.bartolomemejia.imdb.network.MovieService
import com.bartolomemejia.imdb.utils.API_KEY_STRING
import com.bartolomemejia.imdb.utils.ParamsEmun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val service: MovieService) : ViewModel() {
    val listOfMovie = MutableLiveData<List<Movie>>()

    fun searchMovie(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val params = HashMap<String,String>()
            params[ParamsEmun.KEY.string] = API_KEY_STRING
            params[ParamsEmun.PAGE.string] = "1"
            params[ParamsEmun.QUERY.string] = query
            service.searchMovies(params).body()?.results?.let {
                listOfMovie.postValue(it)
            }
        }
    }
}
