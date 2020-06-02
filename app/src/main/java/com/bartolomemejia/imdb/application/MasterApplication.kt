package com.bartolomemejia.imdb.application

import com.bartolomemejia.imdb.di.ApplicationComponent
import com.bartolomemejia.imdb.di.ViewModelModule
import com.bartolomemejia.imdb.network.NetworkModule
import dagger.Component

@Component(modules = [NetworkModule::class, AppModule::class, ViewModelModule::class])
interface RootComponent{
    fun getRootComponent():ApplicationComponent
}

object App{
    val rootFactory = DaggerRootComponent.create()
}