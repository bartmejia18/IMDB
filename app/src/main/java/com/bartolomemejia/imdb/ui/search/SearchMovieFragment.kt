package com.bartolomemejia.imdb.ui.search

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bartolomemejia.imdb.R
import com.bartolomemejia.imdb.application.MasterApplication
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class SearchMovieFragment : Fragment() {

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injection()
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    private fun injection() {
        MasterApplication.rootFactory.getRootComponent().inject(this)
    }

}
