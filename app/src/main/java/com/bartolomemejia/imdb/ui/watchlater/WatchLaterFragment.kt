package com.bartolomemejia.imdb.ui.watchlater

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.bartolomemejia.imdb.R
import com.bartolomemejia.imdb.application.MasterApplication
import com.bartolomemejia.imdb.model.Movie
import com.bartolomemejia.imdb.ui.home.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_watch_later.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class WatchLaterFragment : Fragment(), MovieListAdapter.MovieListClickListener {

    @Inject
    lateinit var viewModel: WatchLaterViewModel
    lateinit var adapter: MovieListAdapter
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injection()
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        adapter = MovieListAdapter(requireContext(), this)
        return inflater.inflate(R.layout.fragment_watch_later, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.watchLaterMovieList.observe(viewLifecycleOwner, Observer {
            adapter.newMovies(it)
            setupRecyclerView()
        })
    }

    private fun setupRecyclerView() {
        watchLaterRecyclerView.adapter = adapter
    }

    private fun injection() {
        MasterApplication.rootFactory.getRootComponent().inject(this)
    }

    override fun onClick(movie: Movie) {
        val bundle = Bundle()
        bundle.putParcelable("movieSelected", movie)
        navController.navigate(R.id.list_to_detail, bundle)
    }

}
