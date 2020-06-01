package com.bartolomemejia.imdb.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bartolomemejia.imdb.ui.favorites.FavoritesFragment
import com.bartolomemejia.imdb.ui.movies.MoviesFragment

class HomePagerAdapter (fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MoviesFragment()
            1 -> FavoritesFragment()
            else -> MoviesFragment()

        }
    }
}