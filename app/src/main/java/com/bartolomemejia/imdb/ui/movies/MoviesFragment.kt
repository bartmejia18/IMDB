package com.bartolomemejia.imdb.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bartolomemejia.imdb.R
import com.bartolomemejia.imdb.application.MasterApplication
import com.bartolomemejia.imdb.model.Movie
import com.bartolomemejia.imdb.ui.home.MovieListAdapter
import com.bartolomemejia.imdb.utils.PaginationScrollListener
import kotlinx.android.synthetic.main.movies_fragment.*
import javax.inject.Inject

class MoviesFragment : Fragment(), MovieListAdapter.MovieListClickListener {

    @Inject
    lateinit var viewModel: MoviesViewModel
    lateinit var adapter: MovieListAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var navController: NavController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        injection()
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        adapter = MovieListAdapter(requireContext(),this)
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var isLoading = false
        progressBar.startAnimation()

        layoutManager = GridLayoutManager(context,2)
        moviesRecyclerView.layoutManager = layoutManager
        moviesRecyclerView.setHasFixedSize(true)

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            if (adapter.list.isEmpty()) {
                adapter.newMovies(it)
                setupRecyclerView()
            } else {
                adapter.newMovies(it)
                adapter.notifyDataSetChanged()
            }
            viewModel.page.value = viewModel.page.value!! + 1
            progressBar.stopAnimation()
            swipeRefresh.isRefreshing = false
        })


        moviesRecyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager as GridLayoutManager) {
            override fun isLastPage(): Boolean {
                return false
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                progressBar.startAnimation()
                viewModel.nextPage()
            }
        })
        swipeRefresh.setOnRefreshListener {
            if (!isLoading){
                isLoading = true
                viewModel.updateMovieList()
            }
        }
    }

    private fun injection() {
        MasterApplication.rootFactory.getRootComponent().inject(this)
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

private fun ProgressBar.startAnimation() {
    max = 10
    visibility = View.VISIBLE
}

private fun ProgressBar.stopAnimation() {
    max = 0
    visibility = View.GONE
}
