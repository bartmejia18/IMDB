package com.bartolomemejia.imdb.ui.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bartolomemejia.imdb.R
import com.bartolomemejia.imdb.data.MovieDataBase
import com.bartolomemejia.imdb.model.Movie
import com.bartolomemejia.imdb.utils.inflate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListAdapter(private val context: Context, val click: MovieListClickListener) :
    RecyclerView.Adapter<MovieListAdapter.MovieListHolder>() {

    var list: MutableList<Movie> = mutableListOf()
    private val movieDao = MovieDataBase.getDatabase(context).movieDao()
    private val favoritesId: MutableList<Int> = mutableListOf()

    init {
        updateDataBase()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder {
        return MovieListHolder(
            parent.context.inflate(R.layout.item_movie, parent, false),
            click
        )
    }

    fun newMovies(newData: List<Movie>){
        list.clear()
        list.addAll(newData)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        holder.bind(position)
    }

    inner class MovieListHolder(
        private val view: View,
        private val click: MovieListClickListener
    ) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) = view.apply {
            val movie = list[position]
            setOnClickListener { click.onClick(movie) }
            titleMovie.text = movie.title
            Glide.with(view.context).load(movie.posterUrl).into(posterMovie)

            toggleButton.isChecked = favoritesId.contains(movie.movieId)
            toggleButton.setOnClickListener {
                if (toggleButton.isChecked) {
                    val favoriteMovie = Movie(movie)
                    favoriteMovie.isFavorite = true
                    insertMovieToDB(favoriteMovie)
                } else {
                    movie.isFavorite = false
                    deleteMovieFromFavorites(movie)
                }
            }
        }
    }

    private fun updateDataBase(){
        CoroutineScope(Dispatchers.IO).launch {
            val data = movieDao.getFavorites()
            favoritesId.clear()
            favoritesId.addAll(data.map { it.movieId })
        }
    }

    private fun insertMovieToDB(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.insertMovie(movie)
            updateDataBase()
        }
    }


    private fun deleteMovieFromFavorites(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteMovieFromFavoritesBy(movie.movieId)
            updateDataBase()
        }
    }

    interface MovieListClickListener {
        fun onClick(movie: Movie)
    }
}
