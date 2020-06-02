package com.bartolomemejia.imdb.di

import com.bartolomemejia.imdb.ui.favorites.FavoritesFragment
import com.bartolomemejia.imdb.ui.home.HomeFragment
import com.bartolomemejia.imdb.ui.movies.MoviesFragment
import dagger.Subcomponent

@Subcomponent
interface ApplicationComponent {
    fun inject(favoritesFragment: FavoritesFragment)
    fun inject(moviesFragment: MoviesFragment)
}