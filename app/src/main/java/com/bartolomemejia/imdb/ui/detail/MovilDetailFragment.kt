package com.bartolomemejia.imdb.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bartolomemejia.imdb.databinding.MovilDetailFragmentBinding
import com.bartolomemejia.imdb.model.Movie

class MovilDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val movieSelected = arguments?.getParcelable<Movie>("movieSelected")
        val binding = MovilDetailFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        movieSelected?.let {
            binding.movie = it

            (activity as AppCompatActivity?)?.supportActionBar?.title = it.title

            //binding.likeAdd.isChecked = it.isFavorite
            binding.detailRatingBar.rating = it.getRating
        }

        return binding.root
    }

}
