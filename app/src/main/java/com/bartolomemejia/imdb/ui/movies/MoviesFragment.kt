package com.bartolomemejia.imdb.ui.movies

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.bartolomemejia.imdb.R
import com.bartolomemejia.imdb.application.App
import javax.inject.Inject

class MoviesFragment : Fragment() {

    @Inject
    lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injection()
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    private fun injection() {
        App.rootFactory.getRootComponent().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.movieList.observe(this, Observer {
            Log.d("PRUEBA 1", it.toString())
        })
        viewModel.updateMovieList()

    }

}
