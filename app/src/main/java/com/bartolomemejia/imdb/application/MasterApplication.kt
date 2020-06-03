package com.bartolomemejia.imdb.application

import android.app.Application
import com.bartolomemejia.imdb.di.ApplicationComponent
import com.bartolomemejia.imdb.di.ViewModelModule
import com.bartolomemejia.imdb.network.NetworkModule
import dagger.Component

class MasterApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        rootFactory = DaggerRootComponent.builder().appModule(AppModule(this)).build()
    }

    companion object{
        lateinit var rootFactory:RootComponent
    }
}

@Component(modules = [NetworkModule::class, AppModule::class, ViewModelModule::class])
interface RootComponent{
    fun getRootComponent():ApplicationComponent

    @Component.Builder
    interface Builder {
        fun build(): RootComponent
        fun appModule(appModule: AppModule): Builder
    }
}
