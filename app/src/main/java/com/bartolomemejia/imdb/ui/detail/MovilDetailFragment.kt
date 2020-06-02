package com.bartolomemejia.imdb.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.bartolomemejia.imdb.R
import com.bartolomemejia.imdb.model.Movie

class MovilDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val movieSelected = arguments?.getParcelable<Movie>("movieSelected")
        val binding =

        return inflater.inflate(R.layout.movil_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


}
