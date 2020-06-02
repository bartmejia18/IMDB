package com.bartolomemejia.imdb.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.bartolomemejia.imdb.R
import com.bartolomemejia.imdb.application.App
import com.bartolomemejia.imdb.model.Movie
import com.bartolomemejia.imdb.ui.home.MovieListAdapter
import kotlinx.android.synthetic.main.movies_fragment.*
import javax.inject.Inject

class MoviesFragment : Fragment(), MovieListAdapter.MovieListClickListener {

    @Inject
    lateinit var viewModel: MoviesViewModel
    var adapter = MovieListAdapter(this)
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injection()
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        viewModel.movieList.observe(this, Observer {
            adapter.list = it
        })
        viewModel.updateMovieList()

    }

    private fun injection() {
        App.rootFactory.getRootComponent().inject(this)
    }

    private fun setupRecyclerView(){
        moviesRecyclerView.adapter = adapter
    }

    override fun onClick(movie: Movie) {
        viewModel.selectedMovie.value = movie
        val bundle = Bundle()
        bundle.putParcelable("movieSelected", movie)
        navController.navigate(R.id.list_to_detail, bundle)
    }


}
