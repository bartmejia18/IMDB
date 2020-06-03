package com.bartolomemejia.imdb.application

import android.app.Application
import com.bartolomemejia.imdb.data.MovieDataBase
import dagger.Module
import dagger.Provides

@Module
class AppModule (val app: Application) {

    @Provides
    fun getApplication() = app

    @Provides
    fun getDataBase(application: Application) = MovieDataBase.getDatabase(application)

    @Provides
    fun getDAO(dataBase: MovieDataBase) = dataBase.movieDao()
}