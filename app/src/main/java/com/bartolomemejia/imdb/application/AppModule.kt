package com.bartolomemejia.imdb.application

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule (val app: Application) {

    @Provides
    fun getApplication() = app
}