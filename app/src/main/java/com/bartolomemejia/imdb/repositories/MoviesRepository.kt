package com.bartolomemejia.imdb.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.bartolomemejia.imdb.model.Movie
import com.bartolomemejia.imdb.services.MovieService
import com.bartolomemejia.imdb.utils.API_KEY_STRING
import com.bartolomemejia.imdb.utils.ParamsEmun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class MoviesRepository @Inject constructor(val service: MovieService){

    val listOfMovies = MutableLiveData<List<Movie>>()


    fun fetchPokeListData() = CoroutineScope(Dispatchers.IO).launch {
        retrievePokesFromNetwork()
    }

    @WorkerThread
    private suspend fun retrievePokesFromNetwork(){

        val params = HashMap<String, String> ()
        params[ParamsEmun.KEY.string] = API_KEY_STRING
        params[ParamsEmun.YEAR.string] = Calendar.getInstance().get(Calendar.YEAR).toString()
        params[ParamsEmun.PAGE.string] = "1"

        val movies = service.getMoviesData(params)
        listOfMovies.postValue(movies.body()?.results ?: emptyList())
    }
}