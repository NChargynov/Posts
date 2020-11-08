package com.chargynov.posts

import android.app.Application
import com.chargynov.posts.di.postsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(postsModule)
        }
    }
}