package com.bartolomemejia.imdb.ui.search

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bartolomemejia.imdb.R
import com.bartolomemejia.imdb.application.MasterApplication
import com.bartolomemejia.imdb.model.Movie
import com.bartolomemejia.imdb.ui.home.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_search_movie.*
import javax.inject.Inject

class SearchMovieFragment : Fragment(), MovieListAdapter.MovieListClickListener {

    @Inject
    lateinit var viewModel: SearchViewModel
    lateinit var adapter: MovieListAdapter
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injection()
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView.onActionViewExpanded()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchMovie(newText)
                return false
            }
        })

        viewModel.listOfMovie.observe(viewLifecycleOwner, Observer {
            adapter = MovieListAdapter(requireContext(), this)
            adapter.newMovies(it)
            setupRecyclerView()
        })
    }

    private fun setupRecyclerView() {
        searchRecyclerView.adapter = adapter
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
