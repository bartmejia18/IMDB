package com.bartolomemejia.imdb.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bartolomemejia.imdb.R
import com.bartolomemejia.imdb.model.Movie
import com.bartolomemejia.imdb.utils.inflate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(val click: MovieListClickListener) :
    RecyclerView.Adapter<MovieListAdapter.MovieListHolder>() {

    var list: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder {
        return MovieListHolder(
            parent.context.inflate(R.layout.item_movie, parent, false),
            click
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        holder.bind(position)
    }

    inner class MovieListHolder(
        val view: View,
        val click: MovieListClickListener
    ) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) = view.apply {
            val movie = list[position]
            setOnClickListener { click.onClick(movie) }
            titleMovie.text = movie.title
            Glide.with(view.context).load(movie.posterUrl).into(posterMovie)
        }
    }


    interface MovieListClickListener {
        fun onClick(movie: Movie)
    }


}
