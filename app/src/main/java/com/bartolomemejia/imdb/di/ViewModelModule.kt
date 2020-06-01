package com.bartolomemejia.imdb.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bartolomemejia.imdb.ui.favorites.FavoritesViewModel
import com.bartolomemejia.imdb.ui.movies.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    internal abstract fun mViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    internal abstract fun fViewModel(viewModel: FavoritesViewModel): ViewModel
}