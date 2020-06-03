package com.bartolomemejia.imdb.ui.favorites

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
import kotlinx.android.synthetic.main.favorites_fragment.*
import javax.inject.Inject

class FavoritesFragment : Fragment(), MovieListAdapter.MovieListClickListener {

    @Inject
    lateinit var viewModel: FavoritesViewModel
    lateinit var adapter: MovieListAdapter
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injection()
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        adapter = MovieListAdapter(requireContext(), this)
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        viewModel.favoriteList.observe(viewLifecycleOwner, Observer {
            adapter.list = it
        })
    }

    private fun setupRecyclerView() {
        favoritesRecyclerView.adapter = adapter
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            viewModel.loadListFavorites()
        }
    }

    private fun injection() {
        MasterApplication.rootFactory.getRootComponent().inject(this)
    }

    override fun onClick(movie: Movie) {
        viewModel.selectedMovie.value = movie
        val bundle = Bundle()
        bundle.putParcelable("movieSelected", movie)
        navController.navigate(R.id.list_to_detail, bundle)
    }

}
