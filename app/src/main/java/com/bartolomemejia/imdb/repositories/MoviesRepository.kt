package com.bartolomemejia.imdb.repositories

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.bartolomemejia.imdb.R
import com.bartolomemejia.imdb.data.MovieDao
import com.bartolomemejia.imdb.model.Movie
import com.bartolomemejia.imdb.network.MovieService
import com.bartolomemejia.imdb.utils.API_KEY_STRING
import com.bartolomemejia.imdb.utils.NetworkUtils
import com.bartolomemejia.imdb.utils.ParamsEmun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class MoviesRepository @Inject constructor(val service: MovieService, val movieDao: MovieDao, val app: Application){

    private val networkUtils by lazy { NetworkUtils(app) }
    val listOfMovies = MutableLiveData<List<Movie>>()
    val lastPage = MutableLiveData<Int>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val movieData = movieDao.getCache()
            if (movieData.isEmpty()) {
                fetchMovieListData(1)
            } else {
                listOfMovies.postValue(movieData)
                lastPage.postValue(2)
            }
        }
    }

    fun fetchMovieListData(page: Int) = CoroutineScope(Dispatchers.IO).launch {
        retrieveMoviesFromNetwork(page)
    }

    @WorkerThread
    private suspend fun retrieveMoviesFromNetwork(page: Int){
        if (page > lastPage.value?.toInt() ?: 1) {
            return
        }

        if (networkUtils.isConnected()) {
            val params = HashMap<String, String>()
            params[ParamsEmun.KEY.string] = API_KEY_STRING
            params[ParamsEmun.YEAR.string] = Calendar.getInstance().get(Calendar.YEAR).toString()
            params[ParamsEmun.PAGE.string] = "$page"

            val movies = service.getMoviesData(params).body()
            movies?.let {
                lastPage.postValue(movies.totalPages)
                if (page > 1) {
                    listOfMovies.value?.toMutableList()?.let {
                        it.addAll(movies.results)
                        listOfMovies.postValue(it)
                    }
                } else {
                    listOfMovies.postValue(movies.results)
                    movieDao.deleteCache()
                    movieDao.insertMovies(movies.results)
                }
            }
        } else {
            val moviesData = movieDao.getCache()
            if (moviesData.isNotEmpty()) {
                listOfMovies.postValue(moviesData)
            }

            withContext(Dispatchers.Main) {
                Toast.makeText(app, R.string.error_network, Toast.LENGTH_LONG).show()
            }
        }
    }
}