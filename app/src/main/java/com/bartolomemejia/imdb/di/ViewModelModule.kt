package com.bartolomemejia.imdb.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bartolomemejia.imdb.ui.favorites.FavoritesViewModel
import com.bartolomemejia.imdb.ui.movies.MoviesViewModel
import com.bartolomemejia.imdb.ui.search.SearchViewModel
import com.bartolomemejia.imdb.ui.watchlater.WatchLaterFragment
import com.bartolomemejia.imdb.ui.watchlater.WatchLaterViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(WatchLaterViewModel::class)
    internal abstract fun wlViewModel(viewModel: WatchLaterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun sViewModel(viewModel: SearchViewModel): ViewModel
}